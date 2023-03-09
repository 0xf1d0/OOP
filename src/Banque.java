import java.util.ArrayList;

/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:17
 */
public class Banque {
    private final ArrayList<CompteBancaire> comptes;
    private final ArrayList<Client> clients;
    private final String nom;

    public Banque(String nomBanque) {
        this.nom = nomBanque;
        this.comptes = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public Particulier creerParticulier(String nomParticulier, String prenom, String adresse) {
        if (rechercheClient(nomParticulier) != null)
            return null;
        Particulier p = new Particulier(nomParticulier, prenom, adresse);
        clients.add(p);
        return p;
    }

    public Entreprise creerEntreprise(String nomEntreprise, int numSIRET, String adresse) {
        if (rechercheClient(nomEntreprise) != null)
            return null;
        Entreprise e = new Entreprise(nomEntreprise, numSIRET, adresse);
        clients.add(e);
        return e;
    }

    public CompteBancaire creerCompteBancaire(double soldeInitial, String nomClient) {
        Client client = rechercheClient(nomClient);
        if (client == null || this.rechercheCompte(nomClient).size() == 3)
            return null;
        CompteBancaire c = new CompteBancaire(soldeInitial, client);
        comptes.add(c);
        return c;
    }

    public CompteBancaireRemunere creerCompteRemunere(double soldeInitial, double tauxInteret, String nomClient) {
        Client client = rechercheClient(nomClient);
        if (client == null || this.rechercheCompte(nomClient).size() == 3)
            return null;
        CompteBancaireRemunere c = new CompteBancaireRemunere(soldeInitial, tauxInteret, client);
        comptes.add(c);
        return c;
    }

    public ArrayList<CompteBancaire> rechercheCompte(String nomClient) {
        ArrayList<CompteBancaire> result = new ArrayList<>();
        for (CompteBancaire c : comptes) {
            if (c.donneDetenteur().donneNom().equals(nomClient))
                result.add(c);
        }
        return result;
    }

    public CompteBancaire rechercheCompte(int numeroCpte) {
        int i = 0;
        while (i < comptes.size()) {
            CompteBancaire c = comptes.get(i);
            if (c.donneNumero() == numeroCpte)
                return c;
            i++;
        }
        return null;
    }

    public boolean supprimerCompte(int numeroCpte) {
        return comptes.remove(this.rechercheCompte(numeroCpte));
    }

    public Client rechercheClient(String nom) {
        int i = 0;
        while (i < clients.size()) {
            Client c = clients.get(i);
            if (c.donneNom().equals(nom))
                return c;
            i++;
        }
        return null;
    }

    public boolean supprimerClient(String nomClient) {
        if (this.rechercheCompte(nomClient).isEmpty())
            return clients.remove(this.rechercheClient(nomClient));
        return false;
    }

    public boolean transfertInterBancaire(int numeroCpteDebiteur, Banque banqueCrediteur, int numeroCpteCrediteur, double montant) {
        CompteBancaire credit = banqueCrediteur.rechercheCompte(numeroCpteCrediteur);
        CompteBancaire debit = this.rechercheCompte(numeroCpteDebiteur);
        boolean r = credit != null && debit != null;
        if (r) {
            debit.debiter(montant + this.getCoutTransfert());
            credit.crediter(montant);
        }
        return r;
    }

    public int getCoutTransfert() {
        return 5;
    }

    @Override
    public String toString() {
        return "Banque{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
