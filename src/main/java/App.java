import Dao.BanqueDAO;
import Dao.ClientDAO;
import Dao.CompteDAO;
import entite.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");

        BanqueDAO banqueDAO = new BanqueDAO(emf);
        ClientDAO clientDAO = new ClientDAO(emf);
        CompteDAO compteDAO = new CompteDAO(emf);

        Banque banque = new Banque();
        banque.setNom("Banque Diginamic");
        banqueDAO.create(banque);

        clientDAO.create(new Client("Bueno-Barthe", "Gaël", new Adresse("101 Rue du developpeur", "Montpellier", "34000", "France"), banque));
        clientDAO.create(new Client("Syla", "Séga", new Adresse("202 Rue du bug", "Toulouse", "31000", "France"), banque));
        Client client3 = new Client("Musk", "Elon", new Adresse("303 SapceX Street", "Houston", "77001", "USA"), banque);
        clientDAO.create(client3);

        Compte compte = new Compte("123456789", 3250.0, Arrays.asList(clientDAO.read(1L), clientDAO.read(2L)));
        compteDAO.create(compte);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer la date d'expiration de l'assurance vie au format AAAA-MM-JJ: ");
        String dateFinStr = scanner.nextLine();

        try {
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinStr);
            AssuranceVie assuranceVie = new AssuranceVie("987654321", 5000.0, List.of(client3), dateFin, 2.5);
            compteDAO.create(assuranceVie);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LivretA livretA = new LivretA("1122334455", 3000.0, List.of(client3), 1.5);
        compteDAO.create(livretA);

        Virement virement = new Virement(new Date(), 200.0, "Virement a Musk", compte, "Elon Musk");
        compteDAO.create(virement);

        Operation operation = new Operation(new Date(), 100.0, "retrait d'especes", compte);
        compteDAO.create(operation);

        clientDAO.close();
        compteDAO.close();
        emf.close();
    }
}