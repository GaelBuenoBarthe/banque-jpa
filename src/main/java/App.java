import entite.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Banque banque = new Banque();
        banque.setNom("Banque Diginamic");
        em.persist(banque);

        Adresse adresse1 = new Adresse();
        adresse1.setRue("101 Rue du developpeur");
        adresse1.setVille("Montpellier");
        adresse1.setCodePostal("34000");
        adresse1.setPays("France");

        Client client1 = new Client("Bueno-Barthe", "Gaël", adresse1, banque);
        em.persist(client1);

        Client client2 = new Client("Syla", "Séga", adresse1, banque);
        em.persist(client2);

        Compte compte = new Compte("123456789", 3250.0, client1);
        em.persist(compte);

        client1.getComptes().add(compte);
        client2.getComptes().add(compte);

        Adresse adresse2 = new Adresse();
        adresse2.setRue("202 Rue du bug");
        adresse2.setVille("Toulouse");
        adresse2.setCodePostal("31000");
        adresse2.setPays("France");

        Client client3 = new Client("Musk", "Elon", adresse2, banque);
        em.persist(client3);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the end date for AssuranceVie (yyyy-MM-dd): ");
        String dateFinStr = scanner.nextLine();

        AssuranceVie assuranceVie = null;
        try {
            Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinStr);
            assuranceVie = new AssuranceVie("987654321", 5000.0, client3, dateFin, 2.5);
            em.persist(assuranceVie);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LivretA livretA = new LivretA("1122334455", 3000.0, client3, 1.5);
        em.persist(livretA);

        client3.getComptes().add(assuranceVie);
        client3.getComptes().add(livretA);

        Virement virement = new Virement(new Date(), 200.0, "Virement a Musk", compte, "Elon Musk");
        em.persist(virement);

        Operation operation = new Operation(new Date(), 100.0, "retrait d'especes", compte);
        em.persist(operation);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}