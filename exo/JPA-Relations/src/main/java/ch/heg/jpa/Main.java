package ch.heg.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        Country suisse = new Country("Suisse");
        City geneve = new City("Geneve");
        City lausanne = new City("Lausanne");

        suisse.add(geneve);
        suisse.add(lausanne);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("countryPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(suisse);
        em.getTransaction().commit();


    }
}
