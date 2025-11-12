package ch.heg.cours6341.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.*;

class PersonManagerTest {
  private static final Person UNEXISTING_PERSON = new Person("toto@toto.com", "toto", 10);
  private static final Person EXISTING_PERSON = new Person("sarah@hepia.ch", "Sarahh", 23);
  PersonManager personManager;

  @BeforeEach
  void setup() throws Exception {
    personManager = new PersonManager();
    try {
      personManager.delete(EXISTING_PERSON);
    } catch (UnknownPersonException _) {
    }
    try {
      personManager.delete(UNEXISTING_PERSON);
    } catch (UnknownPersonException _) {
    }
    personManager.insert(EXISTING_PERSON);
  }

  @AfterEach
  void tearDown() throws Exception {
    try {
      personManager.delete(EXISTING_PERSON);
    } catch (UnknownPersonException _) {
    }
    try {
      personManager.delete(UNEXISTING_PERSON);
    } catch (UnknownPersonException _) {
    }
  }

  @Test
  void getAll() {
    final List<Person> persons = PersonManager.getAll();

    assertEquals(3, persons.size());
  }

  @Test
  void insert() throws PersonAlreadyExistException {
    personManager.insert(UNEXISTING_PERSON);
  }

  @Test
  void insertAlreadyExistingPerson() {
    assertThrows(PersonAlreadyExistException.class, () -> personManager.insert(EXISTING_PERSON));
  }

  @Test
  void update() throws UnknownPersonException {
    personManager.update(EXISTING_PERSON);
  }

  @Test
  void updateInexistentPerson() {
    assertThrows(UnknownPersonException.class, () -> personManager.update(UNEXISTING_PERSON));
  }

  @Test
  void delete() throws UnknownPersonException {
    personManager.delete(EXISTING_PERSON);
  }

  @Test
  void deleteUnexistentPerson() {
    assertThrows(UnknownPersonException.class, () -> personManager.delete(UNEXISTING_PERSON));
  }

  @Test
  void findByExistingEmail() throws UnknownPersonException {
    personManager.findByEmail("sarah@hepia.ch");
  }

  @Test
  void findByUnexistingEmail() {
    assertThrows(UnknownPersonException.class, () -> personManager.findByEmail("toto@heg.ch"));
  }
}
