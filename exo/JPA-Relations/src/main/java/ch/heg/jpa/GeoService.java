package ch.heg.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class GeoService {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresDev");

    public static void insertCountry(Country country) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
    }

    public static void removeCountry(String countryName) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Country toDelete = entityManager
            .createQuery("select c from Country c where c.name = :name", Country.class)
            .setParameter("name", countryName)
            .getResultList()
            .getFirst();
        entityManager.remove(toDelete);
        entityManager.getTransaction().commit();
    }
}
