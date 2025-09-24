package ch.heg.util.admin;

import lombok.Data;

@Data
public class Person {

    private String email;
    private String name;

    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
