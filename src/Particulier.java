/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:18
 */
public class Particulier extends Client {
    private String prenom;

    public Particulier(String nom, String prenom, String adresse) {
        super(nom, adresse);
        this.prenom = prenom;
    }

    public String donnePrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Particulier{" +
                "prenom='" + prenom + '\'' +
                '}';
    }
}
