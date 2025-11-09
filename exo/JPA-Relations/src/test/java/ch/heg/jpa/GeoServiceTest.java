package ch.heg.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GeoServiceTest {

    private static final String TEST_COUNTRY_NAME = "Switzerland";
    private static final String TEST_CAPITAL_NAME = "Bern";
    private static final String TEST_CITY_NAME = "Zurich";
    private static final String TEST_PERSON_NAME = "John Doe";
    private static final Float TEST_CITY_AREA = 87.14f;
    private static final Float UPDATED_CITY_AREA = 100.5f;

    private Country testCountry;
    private City testCity;
    private Person testPerson;
    private Government testGovernment;

    @Before
    public void setUp() {
        City testCapital = new City(TEST_CAPITAL_NAME);
        testCountry = new Country(TEST_COUNTRY_NAME, testCapital);
        testCity = new City(TEST_CITY_NAME);
        testCity.setArea(TEST_CITY_AREA);
        testPerson = new Person(TEST_PERSON_NAME);
        testGovernment = new Government();
    }

    @After
    public void tearDown() {
        GeoService.clearDb();
    }

    @Test
    public void insertCountry_shouldPersistCountry() {
        GeoService.insertCountry(testCountry);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test
    public void insertCountry_shouldPersistCountryWithCapital() {
        GeoService.insertCountry(testCountry);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test
    public void insertCountry_shouldPersistCountryWithGovernment() {
        testCountry.setGovernment(testGovernment);

        GeoService.insertCountry(testCountry);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test
    public void removeCountry_shouldDeleteExistingCountry() {
        GeoService.insertCountry(testCountry);

        GeoService.removeCountry(TEST_COUNTRY_NAME);

        EntityManager em = GeoService.emf.createEntityManager();
        assertThrows(NoSuchElementException.class, () -> GeoService.findCountryByName(TEST_COUNTRY_NAME, em));
    }

    @Test(expected = Exception.class)
    public void removeCountry_shouldThrowExceptionWhenCountryNotFound() {
        GeoService.removeCountry("NonExistentCountry");
    }

    @Test
    public void findCountryByName_shouldReturnCountryWhenExists() {
        GeoService.insertCountry(testCountry);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test(expected = Exception.class)
    public void findCountryByName_shouldThrowExceptionWhenCountryNotFound() {
        EntityManager em = GeoService.emf.createEntityManager();

        GeoService.findCountryByName("NonExistentCountry", em);

        em.close();
    }

    @Test
    public void updateCountry_shouldUpdateExistingCountry() {
        GeoService.insertCountry(testCountry);
        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);

        testCity.setCountry(foundCountry);
        foundCountry.addCity(testCity);
        GeoService.updateCountry(foundCountry);

        Country updatedCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);

        assertNotNull(updatedCountry);
    }

    @Test
    public void updateCity_shouldUpdateExistingCity() {
        testCountry.addCity(testCity);
        GeoService.insertCountry(testCountry);
        EntityManager em = GeoService.emf.createEntityManager();
        City foundCity = em
                .createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
                .setParameter("name", TEST_CITY_NAME)
                .getSingleResult();
        em.close();

        foundCity.setArea(UPDATED_CITY_AREA);
        GeoService.updateCity(foundCity);

        EntityManager em2 = GeoService.emf.createEntityManager();
        City updatedCity = em2
                .createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
                .setParameter("name", TEST_CITY_NAME)
                .getSingleResult();
        em2.close();

        assertNotNull(updatedCity);
    }

    @Test
    public void addGovernmentMember_shouldAddPersonToGovernment() {
        testCountry.setGovernment(testGovernment);
        GeoService.insertCountry(testCountry);
        EntityManager em = GeoService.emf.createEntityManager();
        em.close();

        Person newPerson = new Person("Jane Doe");
        GeoService.addGovernmentMember(newPerson, testGovernment);

        EntityManager em2 = GeoService.emf.createEntityManager();
        Country updatedCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em2);
        em2.close();

        assertNotNull(updatedCountry);
    }

    @Test
    public void addGovernmentMember_shouldPersistNewPerson() {
        testCountry.setGovernment(testGovernment);
        GeoService.insertCountry(testCountry);

        Person newPerson = new Person("toto");
        GeoService.addGovernmentMember(newPerson, testGovernment);

        EntityManager em = GeoService.emf.createEntityManager();
        long personCount = em.createQuery("SELECT COUNT(p) FROM Person p", Long.class).getSingleResult();
        em.close();

        assertTrue(personCount > 0);
    }

    @Test
    public void art493_shouldClearAllGovernmentMembers() {
        testGovernment.addPerson(testPerson);
        testGovernment.addPerson(new Person("Another Person"));
        testCountry.setGovernment(testGovernment);
        GeoService.insertCountry(testCountry);

        GeoService.art493(testGovernment);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test
    public void art493_shouldHandleEmptyGovernment() {
        testCountry.setGovernment(testGovernment);
        GeoService.insertCountry(testCountry);

        GeoService.art493(testGovernment);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);

        assertNotNull(foundCountry);
    }

    @Test
    public void insertCountry_shouldHandleMultipleCities() {
        City city1 = new City("Geneva");
        City city2 = new City("Basel");
        testCountry.addCity(city1);
        testCountry.addCity(city2);

        GeoService.insertCountry(testCountry);

        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        assertNotNull(foundCountry);
    }

    @Test
    public void updateCountry_shouldMergeDetachedEntity() {
        GeoService.insertCountry(testCountry);
        EntityManager em = GeoService.emf.createEntityManager();
        Country foundCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em);
        em.close();

        GeoService.updateCountry(foundCountry);

        EntityManager em2 = GeoService.emf.createEntityManager();
        Country updatedCountry = GeoService.findCountryByName(TEST_COUNTRY_NAME, em2);
        em2.close();

        assertNotNull(updatedCountry);
    }

    @Test
    public void updateCity_shouldMergeDetachedEntity() {
        testCountry.addCity(testCity);
        GeoService.insertCountry(testCountry);
        EntityManager em = GeoService.emf.createEntityManager();
        City foundCity = em
                .createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
                .setParameter("name", TEST_CITY_NAME)
                .getSingleResult();
        em.close();

        GeoService.updateCity(foundCity);

        EntityManager em2 = GeoService.emf.createEntityManager();
        City updatedCity = em2
                .createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
                .setParameter("name", TEST_CITY_NAME)
                .getSingleResult();
        em2.close();

        assertNotNull(updatedCity);
    }
}
