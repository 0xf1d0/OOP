/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:17
 */
public class CompteBancaire {
    private static int n;
    private int num;
    protected double solde;
    private final Client client;

    public CompteBancaire(int numero, double soldeInitial, Client client) {
        this.num = numero;
        this.solde = soldeInitial;
        this.client = client;
    }

    public CompteBancaire(int numero, Client client) {
        this.num = numero;
        this.client = client;
    }

    public CompteBancaire(double soldeInitial, Client client) {
        this.num = n++;
        this.solde = soldeInitial;
        this.client = client;
    }

    public int donneNumero() {
        return num;
    }

    public Client donneDetenteur() {
        return client;
    }

    public double crediter(double montant) {
        solde += montant;
        return solde;
    }

    public double debiter(double montant) {
        if (montant <= solde)
            solde -= montant;
        return solde;
    }

    public double consulter() {
        return solde;
    }

    public boolean transferer(CompteBancaire unCompte, double montantATransferer) {
        if (montantATransferer <= solde) {
            this.debiter(montantATransferer);
            unCompte.crediter(montantATransferer);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CompteBancaire{" +
                "num=" + num +
                ", solde=" + solde +
                ", client=" + client +
                '}';
    }
}
