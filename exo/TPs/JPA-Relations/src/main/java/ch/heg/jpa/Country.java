package ch.heg.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private Long nbHabitants;

    @OneToMany(mappedBy = "country", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "government_id")
    private Government government;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "capital_id")
    private City capital;

    public Country(String name, City capital) {
        this.name = name;
        this.addCity(capital);
        this.capital = capital;
    }

    public Country() {}

    public void addCity(City city) {
        city.setCountry(this);
        cities.add(city);
    }

    public void setGovernment(Government government) {
        government.setCountry(this);
        this.government = government;
    }
}
