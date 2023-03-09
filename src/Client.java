import java.util.ArrayList;

/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:18
 */
public class Client {
    private final String nom;
    private String adresse;

    private final ArrayList<CompteBancaire> comptes;

    public Client(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        this.comptes = new ArrayList<>();
    }

    public String donneNom() {
        return nom;
    }

    public String donneAdresse() {
        return adresse;
    }

    public void changeAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean ajouteCompte(CompteBancaire compte) {
        if (!comptes.contains(compte))
            return comptes.add(compte);
        return false;
    }

    public boolean supprimerCompte(int numCompte) {
        int i = 0;
        while (i < comptes.size()) {
            if (comptes.get(i).donneNumero() == numCompte) {
                comptes.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public ArrayList<CompteBancaire> donneComptes() {
        return comptes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", comptes=" + comptes +
                '}';
    }
}
