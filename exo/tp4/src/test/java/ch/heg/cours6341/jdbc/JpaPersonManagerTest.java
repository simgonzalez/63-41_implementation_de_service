package ch.heg.cours6341.jdbc;

import static org.junit.Assert.*;

import ch.heg.cours6341.utils.Csv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;

public class JpaPersonManagerTest {
  private static final String EMAIL = "hugo@gmail.com";
  private static final String NAME = "hugo";
  private static final int AGE = 22;

  private JPAPersonManager pm;

  @Before
  public void setUp() {
    pm = new JPAPersonManager();
  }

  @Test
  public void insert_and_find_success() {
    Person person = new Person(EMAIL, NAME, AGE);
    pm.insert(person);

    Person p = pm.findByEmail(EMAIL);
    assertEquals(person, p);
  }

  @Test
  public void find_by_name_success() {
    Person person = new Person(EMAIL, NAME, AGE);
    pm.insert(person);

    Person p = pm.findByName(NAME).getFirst();
    assertEquals(person, p);
  }

  @Test()
  public void findByEmail_throws_when_missing() {
    assertNull(pm.findByEmail(EMAIL));
  }

  @Test
  public void update_success() throws UnknownPersonException {
    Person person1 = new Person(EMAIL, NAME, AGE);
    pm.insert(person1);
    person1.setAge(12);
    pm.update(person1);

    Person p = pm.findByEmail(EMAIL);
    assertEquals(person1, p);
  }

  @Test()
  public void update_throws_when_missing() {
    assertThrows(UnknownPersonException.class, () -> pm.update(new Person(EMAIL, NAME, AGE)));
  }

  @Test
  public void delete_success() throws UnknownPersonException {
    Person p = new Person(EMAIL, NAME, AGE);
    pm.insert(p);
    pm.delete(p);

    assertNull(pm.findByEmail(EMAIL));
  }

  @Test()
  public void delete_throws_when_missing() {
    assertThrows(UnknownPersonException.class, () -> pm.delete(new Person(EMAIL, NAME, AGE)));
  }

  @Test
  public void insert_List_Person_best_effort() {

    // init
    List<Person> persons = new ArrayList<>();
    Person fred = new Person("fred@gmail.com", "Fred", 20);
    Person luca = new Person("luca@heg.com", "Luca", 24);

    Person marie = new Person("marie@heg.com", null, 22);
    Person fred2 = new Person("fred@gmail.com", "Fred", 20);

    persons.add(fred);
    persons.add(luca);
    persons.add(marie);
    persons.add(fred2);

    // executer méthode
    List<Person> failed = pm.insertBestEffort(persons);

    // verifications
    // verifier que la liste des failed contient marie et fred2
    assertTrue(failed.contains(fred2));
    assertEquals(1, failed.size());

    // verifier que en base de données on a fred et luca
    List<Person> inserted = pm.findAll();
    assertTrue(
        inserted.containsAll(persons.stream().filter(person -> !person.equals(fred2)).toList()));
    assertEquals(3, inserted.size());
  }

  @Test
  public void insertOneTransaction_fail() {
    List<Person> persons =
        List.of(new Person("toto@gmail.com", "toto", 10), new Person("toto@gmail.com", "toto", 12));

    assertThrows(PersonAlreadyExistException.class, () -> pm.insertOneTransaction(persons));

    assertFalse(pm.findAll().containsAll(persons));
  }

  @Test
  public void insertOneTransaction_success() throws PersonAlreadyExistException {
    List<Person> persons =
        List.of(new Person("toto@gmail.com", "toto", 10), new Person("tata@gmail.com", "tata", 12));

    pm.insertOneTransaction(persons);

    assertTrue(persons.containsAll(pm.findAll()));
  }

  @Test
  public void insertBestEffortCsv_success() throws IOException {
    pm.insertBestEffort("persons.csv");

    assertEquals(true, pm.findAll().containsAll(Csv.parse("persons.csv", Person.class)));
  }

  @Test
  public void insertBestEffortCsv_fail() throws IOException {
    List<Person> failed = pm.insertBestEffort("personsFailed.csv");

    assertTrue(failed.containsAll(List.of(new Person("toto@gmail.com", "tata", 12))));

    assertEquals(
        true,
        pm.findAll()
            .containsAll(
                Csv.parse("personsFailed.csv", Person.class).stream()
                    .filter(person -> !person.equals(new Person("toto@gmail.com", "tata", 12)))
                    .toList()));
  }
}
