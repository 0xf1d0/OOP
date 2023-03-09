import java.util.List;

public class TestBanqueComplet {
    public static void main(String[] args) {
        Banque banqueCLC = new Banque("CLC");

        // test creation intiale
        Particulier durand = banqueCLC.creerParticulier("Durand", "Robert", "La Rochelle");
        if (durand == null) {
            throw new Error("Probleme sur creation intiale du client Durand");
        }
        // test pour verifier que la creation de doublons n'est pas possible
        durand = banqueCLC.creerParticulier("Durand", "Robert", "La Rochelle");
        if (durand != null) {
            throw new Error("Probleme sur creation client : doublon");
        }
        Particulier martin = banqueCLC.creerParticulier("Martin", "Roger", "Nantes");
        if (martin == null) {
            throw new Error("Probleme sur creation intiale d'un client Martin");
        }
        CompteBancaireRemunere cptDurand = banqueCLC.creerCompteRemunere(100, 5.5, "Durand");
        if (cptDurand == null) {
            throw new Error("Probleme sur creation intiale du compte de Durand");
        }
        CompteBancaireRemunere cptMartin = banqueCLC.creerCompteRemunere(0., 4.5, "Martin");
        if (cptMartin == null) {
            throw new Error("Probleme sur creation intiale du compte de Martin");
        }
        // creation d'un deuxième compte pour Durand
        CompteBancaire cptDurand2 = banqueCLC.creerCompteBancaire(200, "Durand");
        if (cptDurand2 == null) {
            throw new Error("Probleme sur creation intiale du compte de Durand");
        }
        // test pour verifier que la creation d'un compte n'est pas possible
        // lorsque le client n'existe pas...
        CompteBancaireRemunere cptClientInconnu = banqueCLC.creerCompteRemunere(0., 4.5, "Schtroumpf");
        if (cptClientInconnu != null) {
            throw new Error("Probleme sur creation intiale d'un compte avec client inconnu");
        }

        // test recuperation d'un compte de la banque
        CompteBancaire cpt = banqueCLC.rechercheCompte(cptMartin.donneNumero());
        if (!cpt.donneDetenteur().donneNom().equals("Martin")) {
            throw new Error("Probleme sur la recherche du compte de Martin");
        }

        // test recuperation d'un client de la banque
        Client client = banqueCLC.rechercheClient("Durand");
        if (!client.donneNom().equals("Durand")) {
            throw new Error("Probleme sur la recherche du client Durand");
        }

        // test recuperation des comptes d'un client
        List<CompteBancaire> comptes = banqueCLC.rechercheCompte("Durand");
        if (comptes.size() != 2) {
            throw new Error("Probleme sur la recherche des comptes du client Durand");
        }

        // test limite nombre comptes (max 3) par client
        CompteBancaire cpt2 = banqueCLC.creerCompteBancaire(0, "Martin");
        if (cpt2 == null) {
            throw new Error("Probleme sur le nombre autorisé de compte par client");
        }
        CompteBancaire cpt3 = banqueCLC.creerCompteBancaire(0, "Martin");
        if (cpt3 == null) {
            throw new Error("Probleme sur le nombre autorisé de compte par client");
        }
        CompteBancaire cpt4 = banqueCLC.creerCompteBancaire(0, "Martin");
        if (cpt4 != null) {
            throw new Error("Probleme sur le nombre maximal de compte par client");
        }

        // test suppression de compte
        if (!banqueCLC.supprimerCompte(comptes.get(0).donneNumero()) ||
                banqueCLC.rechercheCompte("Durand").size() != 1) {
            throw new Error("Probleme sur la suppression d'un compte du client Durand");
        }

        // test suppression d'un client qui possede encore un compte
        if (banqueCLC.supprimerClient("Durand")) {
            throw new Error("Probleme sur la suppression du client Durand qui possede encore 1 compte");
        }

        // test suppression d'un client sans compte
        if (!banqueCLC.supprimerCompte(comptes.get(1).donneNumero()) ||
                !banqueCLC.rechercheCompte("Durand").isEmpty()) {
            throw new Error("Probleme sur la suppression du dernier compte du client Durand");
        }
        if (!banqueCLC.supprimerClient("Durand")) {
            throw new Error("Probleme sur la suppression du client Durand qui ne possede plus de compte");
        }

        // test transfert interbancaire
        Banque banquePop = new Banque("BanquePop");
        banquePop.creerEntreprise("ALSTOM", 98200344, "48 RUE ALBERT DHALENNE 93400 SAINT-OUEN-SUR-SEINE");
        CompteBancaire cptAlstom = banquePop.creerCompteBancaire(1000, "ALSTOM");

        double soldeMartin = cptMartin.consulter();
        double soldeAlstom = cptAlstom.consulter();
        double montantTransfert = 500.;
        boolean resTransfert = banquePop.transfertInterBancaire(cptAlstom.donneNumero(), banqueCLC, cptMartin.donneNumero(), montantTransfert);
        if (!resTransfert ||
                cptAlstom.consulter() != soldeAlstom - montantTransfert - banquePop.getCoutTransfert() ||
                cptMartin.consulter() != soldeMartin + montantTransfert) {
            throw new Error("Probleme sur le transfert interbancaire");
        }

        System.out.println("Tests OK...");

    }
}
