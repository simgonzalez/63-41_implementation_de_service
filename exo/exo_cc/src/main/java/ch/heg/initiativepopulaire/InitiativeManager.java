package ch.heg.initiativepopulaire;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class InitiativeManager {
  private static EntityManagerFactory emf;
  Logger logger = Logger.getLogger(this.getClass().getName());

  public InitiativeManager(String persistenceUnitName) {
    emf = Persistence.createEntityManagerFactory(persistenceUnitName);
  }

  public void create(Initiative initiative) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(initiative);
    em.getTransaction().commit();
  }

  public Initiative findOne(Initiative initiative) {
    EntityManager em = emf.createEntityManager();
    return em.find(Initiative.class,initiative.id());
  }

  public List<Initiative> findAll() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("select i from Initiative i").getResultList();
  }

  public void update(Initiative i) throws UnknownInitiativeException {
    verifyInitiativeExists(i);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.merge(i);
    em.getTransaction().commit();
  }

  private void verifyInitiativeExists(Initiative p) throws UnknownInitiativeException {
    if (this.findOne(p) == null)
      throw new UnknownInitiativeException("La personne n'existe pas");
  }

  public void delete(Initiative initiative) throws UnknownInitiativeException {
    verifyInitiativeExists(initiative);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Initiative toDelete = em.getReference(initiative);
    em.remove(toDelete);
    em.getTransaction().commit();
  }
}
