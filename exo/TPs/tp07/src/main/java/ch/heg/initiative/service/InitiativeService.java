package ch.heg.initiative.service;

import jakarta.persistence.EntityManager;
import org.apache.log4j.Logger;

import java.util.List;

public class InitiativeService {

    private static final Logger log = Logger.getLogger(InitiativeService.class);


    public Initiative create(Initiative initiative) {
        log.info("Creating initiative: " + initiative.getTitre());
        EntityManager em = ServiceHelper.getEntityManager();
        em.getTransaction().begin();
        em.persist(initiative);
        em.getTransaction().commit();
        return initiative;
    }

    public Initiative find(Long id) {
        log.info("Finding initiative " + id);
        EntityManager em = ServiceHelper.getEntityManager();
        Initiative initiative = em.find(Initiative.class, id);
        return initiative;
    }

    public List<Initiative> findAll() {
        log.info("Listing initiatives");
        EntityManager em = ServiceHelper.getEntityManager();
        List<Initiative> list = em.createQuery("SELECT i FROM Initiative i", Initiative.class).getResultList();
        return list;
    }

    public Initiative update(Initiative initiative) {
        EntityManager em = ServiceHelper.getEntityManager();
        em.getTransaction().begin();
        em.merge(initiative);
        em.getTransaction().commit();
        return initiative;
    }

    public boolean delete(Long id) {
        log.warn("Deleting initiative " + id);
        EntityManager em = ServiceHelper.getEntityManager();
        em.getTransaction().begin();
        Initiative initiative = em.find(Initiative.class, id);
        if (initiative != null) em.remove(initiative);
        em.getTransaction().commit();
        return initiative != null;
    }
}
