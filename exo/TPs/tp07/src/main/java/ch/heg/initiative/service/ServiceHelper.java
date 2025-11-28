package ch.heg.initiative.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ServiceHelper {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("InitiativePU");

    static public ObjectMapper   getObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om;
    }

    static public EntityManager   getEntityManager(){
        return emf.createEntityManager();
    }
}
