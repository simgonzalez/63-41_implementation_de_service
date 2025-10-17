package ch.heg.cours6341.jdbc;

import ch.heg.cours6341.utils.Csv;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class JPAPersonManager {

  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");
  Logger logger = Logger.getLogger(JPAPersonManager.class);

  public JPAPersonManager() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.createQuery("delete from Person").executeUpdate();
    em.getTransaction().commit();
  }

  public void insert(Person p) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(p);
    em.getTransaction().commit();
  }

  public Person findByEmail(String email) {
    EntityManager em = emf.createEntityManager();
    return em.find(Person.class, email);
  }

  public List<Person> findByName(String name) {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("select p from person p where name = :name", Person.class)
        .setParameter("name", name)
        .getResultList();
  }

  public List<Person> findAll() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("select p from Person p").getResultList();
  }

  public void update(Person p) throws UnknownPersonException {
    verifyPersonExist(p);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.merge(p);
    em.getTransaction().commit();
  }

  private void verifyPersonExist(Person p) throws UnknownPersonException {
    if (this.findByEmail(p.getEmail()) == null)
      throw new UnknownPersonException("La personne n'existe pas");
  }

  public void delete(Person p) throws UnknownPersonException {
    verifyPersonExist(p);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Person toDelete = em.getReference(p);
    em.remove(toDelete);
    em.getTransaction().commit();
  }

  public void insertOneTransaction(List<Person> persons) throws PersonAlreadyExistException {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      persons.forEach(em::persist);
    } catch (EntityExistsException e) {
      logger.warn("failed to insert ", e);
      throw new PersonAlreadyExistException("La personne existe déjà en base de données");
    }
    em.getTransaction().commit();
  }

  public List<Person> insertBestEffort(List<Person> persons) {
    List<Person> personsFailedToInsert = new ArrayList<>();
    persons.forEach(
        p -> {
          try {
            this.insert(p);
          } catch (Exception e) {
            logger.warn("failed to insert " + p, e);
            personsFailedToInsert.add(p);
          }
        });
    return personsFailedToInsert;
  }

  public List<Person> insertBestEffort(String fileName) throws IOException {
    List<Person> persons = Csv.parse(fileName, Person.class);

    return insertBestEffort(persons);
  }
}
