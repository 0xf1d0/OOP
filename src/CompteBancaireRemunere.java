/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:18
 */
public class CompteBancaireRemunere extends CompteBancaire {
    public static final double interetParDefaut = 3;
    private double tauxInteret;

    public CompteBancaireRemunere(int numero, double soldeInitial, double tauxInteret, Client c) {
        super(numero, soldeInitial, c);
        this.tauxInteret = tauxInteret;
    }

    public CompteBancaireRemunere(int numero, double soldeInitial, Client c) {
        super(numero, soldeInitial, c);
        this.tauxInteret = interetParDefaut;
    }

    public CompteBancaireRemunere(int numero, Client c) {
        super(numero, 0.0, c);
        this.tauxInteret = interetParDefaut;
    }

    public CompteBancaireRemunere(double soldeInitial, double tauxInteret, Client c) {
        super(soldeInitial, c);
        this.tauxInteret = tauxInteret;
    }

    public void crediterInteretMensuel() {
        this.crediter(this.solde * this.tauxInteret / 12 / 100);
    }

    @Override
    public String toString() {
        return "CompteBancaireRemunere{" +
                "solde=" + solde +
                ", tauxInteret=" + tauxInteret +
                '}';
    }
}
