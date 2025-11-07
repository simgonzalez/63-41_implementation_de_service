package ch.heg.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
class Government {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private List<Person> federalCouncelers = new ArrayList<>();

    @OneToOne(mappedBy = "government")
    private Country country;

    public void addPerson(Person person) {
        federalCouncelers.add(person);
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
