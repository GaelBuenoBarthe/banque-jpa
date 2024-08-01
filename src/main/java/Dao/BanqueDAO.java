package Dao;

import entite.Banque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class BanqueDAO extends BaseDAO {

    public BanqueDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public void create(Banque banque) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(banque);
        em.getTransaction().commit();
        em.close();
    }

    public Banque read(Long id) {
        EntityManager em = getEntityManager();
        Banque banque = em.find(Banque.class, id);
        em.close();
        return banque;
    }

    public void update(Banque banque) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(banque);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Banque banque = em.find(Banque.class, id);
        if (banque != null) {
            em.remove(banque);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Banque> findAll() {
        EntityManager em = getEntityManager();
        List<Banque> banques = em.createQuery("from Banque", Banque.class).getResultList();
        em.close();
        return banques;
    }
}