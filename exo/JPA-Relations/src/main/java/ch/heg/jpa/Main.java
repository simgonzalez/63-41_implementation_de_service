package ch.heg.jpa;

public class Main {

    public static void main(String[] args) {
        Country suisse = new Country("Suisse", new City("Berne"));

        suisse.addCity(new City("Geneve"));
        suisse.addCity(new City("Lausanne"));
        suisse.setGovernment(new Government());

        GeoService.insertCountry(suisse);
        //GeoService.removeCountry("Suisse");
    }
}
