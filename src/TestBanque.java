import java.util.List;

/**
 * Classe de test pour CompteBancaire
 */
public class TestBanque {
    public static void main(String[] args) {
        Banque banqueCLC = new Banque("CLC");

        // test creation intiale
        Particulier durand = banqueCLC.creerParticulier("Durand", "Robert", "La Rochelle");
        if (durand == null) {
            throw new Error("Probleme sur creation initiale du client Durand");
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

        System.out.println("Tests OK...");
    }
}