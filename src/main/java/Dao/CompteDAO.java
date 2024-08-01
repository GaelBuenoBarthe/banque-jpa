package Dao;

import entite.Compte;
import entite.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class CompteDAO extends BaseDAO {

    public CompteDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public void create(Compte compte) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(compte);
        em.getTransaction().commit();
        em.close();
    }

    public void create(Operation operation) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(operation);
        em.getTransaction().commit();
        em.close();
    }

    public Compte read(Long id) {
        EntityManager em = getEntityManager();
        Compte compte = em.find(Compte.class, id);
        em.close();
        return compte;
    }

    public void update(Compte compte) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(compte);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Compte compte = em.find(Compte.class, id);
        if (compte != null) {
            em.remove(compte);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Compte> findAll() {
        EntityManager em = getEntityManager();
        List<Compte> comptes = em.createQuery("from Compte", Compte.class).getResultList();
        em.close();
        return comptes;
    }
}