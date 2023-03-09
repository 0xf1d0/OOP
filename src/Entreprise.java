/**
 * @author Vincent C.
 * @since 07/03/2023 - 19:18
 */
public class Entreprise extends Client {
    private final int numSIRET;

    public Entreprise(String nom, int numSIRET, String adresse) {
        super(nom, adresse);
        this.numSIRET = numSIRET;
    }

    public long donneNumSIRET() {
        return numSIRET;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "numSIRET=" + numSIRET +
                '}';
    }
}
