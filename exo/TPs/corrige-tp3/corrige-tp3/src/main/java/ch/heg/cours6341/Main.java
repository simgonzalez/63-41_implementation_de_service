package ch.heg.cours6341;

import java.sql.SQLException;

import ch.heg.cours6341.jdbc.Person;
import ch.heg.cours6341.jdbc.PersonAlreadyExistException;
import ch.heg.cours6341.jdbc.PersonManager;
import ch.heg.cours6341.jdbc.UnknownPersonException;


public class Main {
    public static void main(String[] args) throws Exception {
        PersonManager repositoriy = new PersonManager();
        Person someone = new Person("someone@gmail.com", "someone", 18);
        repositoriy.insert(someone);
        System.out.println("All persons: " + repositoriy.getAll());
    }
}
