package ch.heg.cours6341.initiative;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class InitiativeManager {

  EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");

  public List<Initiative> findAll() {
    EntityManager entityManager = emf.createEntityManager();
    return entityManager
        .createQuery("Select i from Initiative i", Initiative.class)
        .getResultList();
  }

  public Initiative findById(long id) {
    EntityManager entityManager = emf.createEntityManager();
    return entityManager.find(Initiative.class, id);
  }

  public void insert(Initiative initiative) {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(initiative);
    entityManager.getTransaction().commit();
  }

  public boolean update(Initiative initiative) {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.merge(initiative);
    entityManager.getTransaction().commit();
    return true;
  }

  public boolean delete(Long id) {
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(id);
    entityManager.getTransaction().commit();
    return true;
  }
}
