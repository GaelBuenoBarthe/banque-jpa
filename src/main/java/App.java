import Dao.ClientDAO;
import Dao.CompteDAO;
import entite.*;
import jakarta.persistence.EntityManager;
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

        ClientDAO clientDAO = new ClientDAO();
        CompteDAO compteDAO = new CompteDAO();

        Banque banque = new Banque();
        banque.setNom("Banque Diginamic");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(banque);
        em.getTransaction().commit();
        em.close();

        Adresse adresse1 = new Adresse();
        adresse1.setRue("101 Rue du developpeur");
        adresse1.setVille("Montpellier");
        adresse1.setCodePostal("34000");
        adresse1.setPays("France");

        Client client1 = new Client("Bueno-Barthe", "Gaël", adresse1, banque);
        clientDAO.create(client1);
        Adresse adresse2 = new Adresse();
        adresse2.setRue("202 Rue du bug");
        adresse2.setVille("Toulouse");
        adresse2.setCodePostal("31000");
        adresse2.setPays("France");

        Client client2 = new Client("Syla", "Séga", adresse2, banque);
        clientDAO.create(client2);

        Compte compte = new Compte("123456789", 3250.0, Arrays.asList(client1, client2));
        compteDAO.create(compte);

        Adresse adresse3 = new Adresse();
        adresse3.setRue("303 SapceX Street");
        adresse3.setVille("Houston");
        adresse3.setCodePostal("77001");
        adresse3.setPays("USA");

        Client client3 = new Client("Musk", "Elon", adresse3, banque);
        clientDAO.create(client3);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the end date for AssuranceVie (yyyy-MM-dd): ");
        String dateFinStr = scanner.nextLine();

        AssuranceVie assuranceVie = null;
        try {
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinStr);
            assuranceVie = new AssuranceVie("987654321", 5000.0, List.of(client3), dateFin, 2.5);
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

        emf.close();
    }
}