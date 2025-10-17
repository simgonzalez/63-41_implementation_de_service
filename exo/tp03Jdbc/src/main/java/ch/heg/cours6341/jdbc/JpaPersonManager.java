package ch.heg.cours6341.jdbc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaPersonManager {
  private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("development");

  public void insert(Person person) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(person);
    em.getTransaction().commit();
  }

  public Person findByEmail(String email) {
    EntityManager em = emf.createEntityManager();
    return em.find(Person.class, email);
  }

  public List<Person> findAll() {
    EntityManager em = emf.createEntityManager();
    return em.createQuery("select * from Person").getResultList();
  }

  public void update(Person person) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.merge(person);
    em.getTransaction().commit();
  }
}
