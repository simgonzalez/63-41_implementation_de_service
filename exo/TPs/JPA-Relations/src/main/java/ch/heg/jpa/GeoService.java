package ch.heg.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class GeoService {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static void insertCountry(Country country) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
    }

    public static void removeCountry(String countryName) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Country toDelete = findCountryByName(countryName, entityManager);
        entityManager.remove(toDelete);
        entityManager.getTransaction().commit();
    }

    public static Country findCountryByName(String countryName, EntityManager entityManager) {
        return entityManager
            .createQuery("select c from Country c where c.name = :name", Country.class)
            .setParameter("name", countryName)
            .getResultList()
            .getFirst();
    }

    public static void updateCountry(Country country) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(country);
        entityManager.getTransaction().commit();
    }

    public static void updateCity(City city) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(city);
        entityManager.getTransaction().commit();
    }

    public static void addGovernmentMember(Person governmentMember, Government government) {
        government.addPerson(governmentMember);
        updateCountry(government.getCountry());
    }

    public static void art493(Government government) {
        government.art493();
        updateGovernment(government);
    }

    private static void updateGovernment(Government government) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(government);
        entityManager.getTransaction().commit();
    }

    public static void clearDb() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("select c from Country c").getResultList().forEach(entityManager::remove);
        entityManager.getTransaction().commit();
    }
}
