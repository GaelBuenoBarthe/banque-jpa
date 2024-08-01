package Dao;

import entite.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ClientDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");

    public void create(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    public Client read(Long id) {
        EntityManager em = emf.createEntityManager();
        Client client = em.find(Client.class, id);
        em.close();
        return client;
    }

    public void update(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        if (client != null) {
            em.remove(client);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Client> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = em.createQuery("from Client", Client.class).getResultList();
        em.close();
        return clients;
    }
}