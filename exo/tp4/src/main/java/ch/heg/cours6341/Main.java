package ch.heg.cours6341;

import java.sql.SQLException;

import ch.heg.cours6341.jdbc.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Person someone = new Person("someone@gmail.com", "someone", 18);
        Person someone2 = new Person("someone2@gmail.com", "someone", 18);
        JPAPersonManager personManager = new JPAPersonManager();
        personManager.insert(someone);
        personManager.insert(someone2);
        someone.setAge(20);
        System.out.println(personManager.findByEmail(someone.getEmail()));
        personManager.update(someone);
        System.out.println(personManager.findByEmail(someone.getEmail()));
        System.out.println(personManager.findAll());
    }
}
