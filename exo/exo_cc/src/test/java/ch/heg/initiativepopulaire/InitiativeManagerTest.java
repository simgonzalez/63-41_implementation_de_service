package ch.heg.initiativepopulaire;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.PropertyValueException;
import org.junit.Test;

public class InitiativeManagerTest {

  InitiativeManager initiativeManager = new InitiativeManager("test");

  @Test
  public void whenCreateInitiative_thenInitiativeIsInDb() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);
    assertEquals(i.toString(), initiativeManager.findOne(i).toString());
  }

  @Test
  public void whenCreateIncompleteInitiative_thenInitiativeIsNotInDb() {
    Initiative i = new Initiative();
    assertThrows(PropertyValueException.class, () -> initiativeManager.create(i));
    assertNotEquals(i, initiativeManager.findOne(i));
  }

  @Test
  public void findOne() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);
    assertEquals(i.toString(), initiativeManager.findOne(i).toString());
  }

  @Test
  public void findOneThatDoesntExist() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);
    Initiative i2 = new Initiative();
    assertNotEquals(i.titre(), i2.titre());
  }

  @Test
  public void findAll() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    Initiative i2 =
        new Initiative(
            "Pour la reconnaissance", "toto", LocalDate.of(2020, 10, 9), Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);
    initiativeManager.create(i2);

    List<Initiative> initiatives = initiativeManager.findAll();

    assertEquals(2, initiatives.size());
    assertTrue(initiatives.containsAll(List.of(i, i2)));
  }

  @Test
  public void update() throws UnknownInitiativeException {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);
    i.setDescription("la palestine mérite d'être un État.");
    assertNotEquals(
        "la palestine mérite d'être un État.", initiativeManager.findOne(i).getDescription());
    initiativeManager.update(i);
    assertEquals(i.toString(), initiativeManager.findOne(i).toString());
  }

  @Test
  public void update_throwsIfInitiativeDoesntExist() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);

    assertThrows(
        UnknownInitiativeException.class,
        () ->
            initiativeManager.update(
                new Initiative("toto", "", LocalDate.of(2024, 10, 9), Status.COLLECTE_EN_COURS)));
  }

  @Test
  public void delete() throws UnknownInitiativeException {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);

    initiativeManager.delete(i);

    assertNotEquals(i, initiativeManager.findOne(i));
  }

  @Test
  public void deleteWhenDoesntExist_thenThrows() {
    Initiative i =
        new Initiative(
            "Pour la reconnaissance de l’État de Palestine",
            null,
            LocalDate.of(2024, 10, 9),
            Status.COLLECTE_EN_COURS);
    initiativeManager.create(i);

    assertThrows(
        UnknownInitiativeException.class, () -> initiativeManager.delete(new Initiative()));
  }
}
