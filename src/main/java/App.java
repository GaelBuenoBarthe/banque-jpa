import entite.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Banque banque = new Banque();
        banque.setNom("Banque Diginamic");
        em.persist(banque);

        Adresse adresse = new Adresse();
        adresse.setRue("101 Rue du developpement");
        adresse.setVille("Montpellier");
        adresse.setCodePostal("34000");
        adresse.setPays("France");

        Client client = new Client();
        client.setNom("Bueno-Barthe");
        client.setPrenom("Gaël");
        client.setAdresse(adresse);
        client.setBanque(banque);
        em.persist(client);

        Compte compte = new Compte();
        compte.setNumero("123456789");
        compte.setSolde(1000.0);
        compte.setClient(client);
        em.persist(compte);

        Operation operation = new Operation();
        operation.setDate(new Date());
        operation.setMontant(3200.0);
        operation.setDescription("Depôt d'argent");
        operation.setCompte(compte);
        em.persist(operation);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}