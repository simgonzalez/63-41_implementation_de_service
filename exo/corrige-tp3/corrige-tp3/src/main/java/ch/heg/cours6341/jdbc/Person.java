package ch.heg.cours6341.jdbc;


import java.util.Objects;

public class Person {
    private String email;
    private String name;
    private int age;

    public Person(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge){
        this.age = newAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(email, person.email) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, age);
    }


    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
