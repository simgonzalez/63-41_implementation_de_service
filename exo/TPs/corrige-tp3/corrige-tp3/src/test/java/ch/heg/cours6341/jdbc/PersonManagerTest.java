package ch.heg.cours6341.jdbc;

import org.junit.*;
import java.sql.*;
import static org.junit.Assert.*;

public class PersonManagerTest {

    private static final String EMAIL = "hugo@gmail.com";
    private static final String NAME  = "hugo";
    private static final int AGE     = 22;

    private Connection conn;
    private PersonManager pm;

    @Before
    public void setUp() throws Exception {
        conn = DBHelper.getConnection();
        Statement st = conn.createStatement();
        st.executeUpdate("DELETE FROM person");
        pm = new PersonManager();
    }

    @After
    public void tearDown() throws Exception {
        if (conn != null && !conn.isClosed()) conn.close();
    }

    @Test
    public void insert_and_find_success() throws Exception {
        Person person = new Person(EMAIL, NAME, AGE);
        pm.insert(person);

        Person p = pm.findByEmail(EMAIL);
        assertEquals(person,p);
    }

    @Test()
    public void findByEmail_throws_when_missing() throws Exception {
        assertNull(pm.findByEmail(EMAIL));
    }

    @Test
    public void update_success() throws Exception {
        Person person1 = new Person(EMAIL, NAME, AGE);
        pm.insert(person1);
        person1.setAge(12);
        pm.update(person1);

        Person p = pm.findByEmail(EMAIL);
        assertEquals(person1 , p);
    }

    @Test(expected = UnknownPersonException.class)
    public void update_throws_when_missing() throws Exception {
        pm.update(new Person(EMAIL, NAME, AGE));
    }

    @Test
    public void delete_success() throws Exception {
        pm.insert(new Person(EMAIL, NAME, AGE));
        pm.delete(new Person(EMAIL, NAME, AGE));

        assertEquals(null, pm.findByEmail(EMAIL));
    }

    @Test(expected = UnknownPersonException.class)
    public void delete_throws_when_missing() throws Exception {
        pm.delete(new Person(EMAIL, NAME, AGE));
    }
}
