package Dao;

import entite.Compte;
import entite.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CompteDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");

    public void create(Compte compte) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(compte);
        em.getTransaction().commit();
        em.close();
    }

    public void create(Operation operation) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(operation);
        em.getTransaction().commit();
        em.close();
    }

    public Compte read(Long id) {
        EntityManager em = emf.createEntityManager();
        Compte compte = em.find(Compte.class, id);
        em.close();
        return compte;
    }

    public void update(Compte compte) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(compte);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Compte compte = em.find(Compte.class, id);
        if (compte != null) {
            em.remove(compte);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Compte> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Compte> comptes = em.createQuery("from Compte", Compte.class).getResultList();
        em.close();
        return comptes;
    }
}