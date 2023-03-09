/**
 * classe de test pour CompteBancaire
 */
public class TestCompteBancaire {
    public static void main(String[] args) {
        Client durand = new Client("Durand", "La Rochelle");
        Client martin = new Client("Martin", "Nantes");

        CompteBancaire cptDurand = new CompteBancaire(1, 100, durand);
        // necessaire pour associer Client -> Compte
        if (!durand.ajouteCompte(cptDurand)) {
            throw new Error("Probleme sur l'attribution du compte a : " + durand);
        }
        // test pour verifier qu'on ne peut pas attribuer 2 fois le meme compte
        if (durand.ajouteCompte(cptDurand)) {
            throw new Error("Probleme compte attribue deux fois a : " + durand);
        }

        CompteBancaire cptMartin = new CompteBancaire(2, 0, martin);
        martin.ajouteCompte(cptMartin);

        // tests initialisation
        if (cptDurand.consulter() != 100.) {
            throw new Error("Solde du compte de Durand invalide...");
        }
        if (cptMartin.consulter() != 0.) {
            throw new Error("Solde du compte de Martin invalide...");
        }

        // tests debit/credit
        double solde_c1 = cptDurand.consulter();
        final int montantDebit = 50;
        cptDurand.debiter(montantDebit);
        if (cptDurand.consulter() != solde_c1 - montantDebit) {
            throw new Error("Probleme sur debit de " + cptDurand);
        }
        double solde_c2 = cptMartin.consulter();
        final int montantCredit = 30;
        cptMartin.crediter(montantCredit);
        if (cptMartin.consulter() != solde_c2 + montantCredit) {
            throw new Error("Probleme sur credit de " + cptMartin);
        }

        // test transfert OK
        final int montantTransfert = 50;
        solde_c1 = cptDurand.consulter();
        solde_c2 = cptMartin.consulter();
        // le transfert doit reussir car les conditions sont OK
        if (!cptDurand.transferer(cptMartin, montantTransfert)) {
            throw new Error("Echec du transfert...");
        }
        if ((cptDurand.consulter() != solde_c1 - montantTransfert) || (cptMartin.consulter() != solde_c2 + montantTransfert)) {
            throw new Error("Probleme sur transfert entre " + cptDurand + " et " + cptMartin);
        }

        // test transfert échec
        solde_c1 = cptDurand.consulter();
        solde_c2 = cptMartin.consulter();
        // le transfert doit echouer car cptDurand est a 80
        if (cptMartin.transferer(cptDurand, 90)) {
            throw new Error("Transfert invalide...");
        }
        if (cptDurand.consulter() != solde_c1) {
            throw new Error("Solde du compte de Durand invalide apres transfert...");
        }
        if (cptMartin.consulter() != solde_c2) {
            throw new Error("Solde du compte de Martin invalide apres transfert...");
        }

        System.out.println("Tests OK...");
    }
}