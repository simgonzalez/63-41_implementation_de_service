package ch.heg.util.admin;

public class Person {

    private String email;
    private String name;

    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
