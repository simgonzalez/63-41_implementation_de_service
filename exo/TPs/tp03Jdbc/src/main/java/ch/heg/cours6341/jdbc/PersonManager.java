package ch.heg.cours6341.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonManager {
  public static final String SQL_PRIMARY_KEY_VIOLATION_ERROR = "23505";
  private static final Logger logger = LogManager.getLogger(PersonManager.class);

  public static List<Person> getAll() {
    List<Person> persons = new ArrayList<>();
    try (Connection con = DBHelper.getConnection();
        PreparedStatement pstm = con.prepareStatement("select * from person");
        ResultSet rs = pstm.executeQuery(); ) {
      while (rs.next()) {
        String email = rs.getString("email");
        String name = rs.getString("name");
        int age = rs.getInt("age");

        persons.add(new Person(email, name, age));
      }
      return persons;
    } catch (SQLException e) {
      logger.error("Issue while retriving persons from the database\n");
      throw new RuntimeException(e.getMessage());
    }
  }

  public void insert(Person p) throws PersonAlreadyExistException {
    try {
      findByEmail(p.getEmail());
    } catch (UnknownPersonException e) {
      logger.info("{} doesn't exist so creating it.", p.getEmail());
    }

    try (Connection connection = DBHelper.getConnection();
        PreparedStatement personInsert =
            connection.prepareStatement(
                "insert into person (email, name, age) values (?, ?, ?)"); ) {
      personInsert.setString(1, p.getEmail());
      personInsert.setString(2, p.getName());
      personInsert.setInt(3, p.getAge());
      personInsert.executeUpdate();
    } catch (SQLException e) {
      if (SQL_PRIMARY_KEY_VIOLATION_ERROR.equals(e.getSQLState())) {
        throw new PersonAlreadyExistException(e.getMessage());
      }
      logger.error("Issue while inserting new person in the database\n{}", p);
      throw new RuntimeException(e);
    }
  }

  public void update(Person p) throws UnknownPersonException {
    findByEmail(p.getEmail());

    try (Connection connection = DBHelper.getConnection();
        PreparedStatement personUpdate =
            connection.prepareStatement("update person set name = ?, age = ? where email = ?"); ) {
      personUpdate.setString(1, p.getName());
      personUpdate.setInt(2, p.getAge());
      personUpdate.setString(3, p.getEmail());
      personUpdate.executeUpdate();
    } catch (SQLException e) {
      logger.error("Issue while updating person in the database\n{}", p);
      throw new RuntimeException(e);
    }
  }

  public void delete(Person p) throws UnknownPersonException {
    findByEmail(p.getEmail());

    try (Connection connection = DBHelper.getConnection();
        PreparedStatement personUpdate =
            connection.prepareStatement("delete from person where email = ?"); ) {
      personUpdate.setString(1, p.getEmail());
      personUpdate.executeUpdate();
    } catch (SQLException e) {
      logger.error("Issue while deleting person in the database\n{}", p);
      throw new RuntimeException(e);
    }
  }

  public void findByEmail(String email) throws UnknownPersonException {
    try (Connection connection = DBHelper.getConnection();
        PreparedStatement personUpdate =
            connection.prepareStatement("select * from person where email = ?"); ) {
      personUpdate.setString(1, email);
      try (ResultSet person = personUpdate.executeQuery()) {
        if (!person.next()) {
          throw new UnknownPersonException("Person doesn't exist\n" + email);
        }
      }
    } catch (SQLException e) {
      logger.error("Issue while trying to find person with email: {} in the database", email);
      throw new RuntimeException(e);
    }
  }
}
