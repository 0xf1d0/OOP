import java.util.ArrayList;
import java.util.List;

public class TestEntrepriseParticulier {
    public static void main(String[] args) {
        Particulier durand = new Particulier("Durand", "Robert", "La Rochelle");
        Entreprise alstom = new Entreprise("ALSTOM", 98200344, "48 RUE ALBERT DHALENNE 93400 SAINT-OUEN-SUR-SEINE");

        // verifie que les particuliers et les entreprises sont des clients
        List<Client> clients = new ArrayList<>();
        clients.add(0, durand);
        clients.add(1, alstom);

        if (!clients.get(0).donneNom().equals("Durand")) {
            throw new Error("probleme sur le nom du client Durand");
        }
        if (!clients.get(1).donneNom().equals("ALSTOM")) {
            throw new Error("probleme sur le nom du client ALSTOM");
        }

        System.out.println("Tests OK...");
    }
}
