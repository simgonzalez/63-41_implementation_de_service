package ch.heg.jpa;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float area;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public City(String name) {
        this.name = name;
    }

    public City() {}

    public void setArea(Float area) {
        this.area = area;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
