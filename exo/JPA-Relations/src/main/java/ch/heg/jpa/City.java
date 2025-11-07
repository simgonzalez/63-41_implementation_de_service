package ch.heg.jpa;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Float area;
    @ManyToOne
    @JoinColumn(name = "id_country")
    Country country;

    public City(String name) {
        this.name = name;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
