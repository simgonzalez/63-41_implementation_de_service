package ch.heg.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Government {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Person> federalCouncelers = new ArrayList<>();

    @OneToOne(mappedBy = "government")
    private Country country;

    public void art493() {
        this.federalCouncelers.clear();
    }

    public void addPerson(Person person) {
        federalCouncelers.add(person);
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public Country getCountry() {
        return this.country;
    }
}
