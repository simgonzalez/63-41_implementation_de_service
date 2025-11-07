package ch.heg.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long nbHabitants;

    @OneToMany (mappedBy = "country", cascade = CascadeType.PERSIST)
    List<City> cities = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public void add(City city) {
        city.setCountry(this);
        cities.add(city);

    }


}
