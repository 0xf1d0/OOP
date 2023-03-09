/**
 * classe de test pour CompteBancaire
 */
public class TestCompteBancaireRemunere {
    public static void main(String[] args) {
        Client durand = new Client("Durand", "La Rochelle");
        Client martin = new Client("Martin", "Angoulins");
        CompteBancaireRemunere cptDurand = new CompteBancaireRemunere(100, 50, 6, durand);
        CompteBancaireRemunere cptMartin = new CompteBancaireRemunere(200, 30, martin);

        // ajout des interets mensuels...
        cptDurand.crediterInteretMensuel();
        if (cptDurand.consulter() != 50.25) {
            throw new Error("Solde du compte de Durand invalide apres calcul des interets...");
        }

        cptMartin.crediterInteretMensuel();
        if (cptMartin.consulter() != 30.075) {
            throw new Error("Solde du compte de Martin invalide apres calcul des interets...");
        }

        System.out.println("Tests OK...");
    }
}