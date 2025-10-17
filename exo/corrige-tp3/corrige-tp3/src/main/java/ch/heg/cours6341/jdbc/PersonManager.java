package ch.heg.cours6341.jdbc;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {

    Logger log = Logger.getLogger(PersonManager.class);
    
    public List<Person> getAll() throws SQLException {
        List<Person> persons = new ArrayList<>();
        Connection connection = DBHelper.getConnection();
        PreparedStatement pstm = connection.prepareStatement("select * from person");
        ResultSet rs = pstm.executeQuery();

        while(rs.next()){
            String email = rs.getString("email");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            persons.add(new Person(email, name, age));
        }
        return persons;
    }


    public void insert(Person p) throws PersonAlreadyExistException, PersonException {
        String sql = "INSERT INTO person (email, name, age) VALUES (?, ?, ?)";
        try{
        Connection connection = DBHelper.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getEmail());
        ps.setString(2, p.getName());
        ps.setInt(3, p.getAge());

            ps.executeUpdate();
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException dup) {
            throw new PersonAlreadyExistException("already used");
        }catch (SQLException e){
            log.error("Failed to insert " + p, e);
            throw new PersonException("Failed to insert " + p);
        }
    }

    // Levez UnknownPersonException si la personne n'existe pas
    public void update ( Person p) throws UnknownPersonException, SQLException{
        String sql = "UPDATE person SET name = ?, age =? where email = ?";
        Connection connection = DBHelper.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setInt(2, p.getAge());
        ps.setString(3, p.getEmail());

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated == 0){
            throw new UnknownPersonException("person not found");
        }
    }

    // Levez UnknownPersonException si la personne n'existe pas
    public void delete ( Person p) throws UnknownPersonException, SQLException{
        String sql = "DELETE from person where email = ?";
        Connection connection = DBHelper.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, p.getEmail());
        
        int rowDeleted = ps.executeUpdate();
        
        if (rowDeleted == 0){
            throw new UnknownPersonException("person not found");
        }
    }

    // Levez UnknownPersonException si la personne n'existe pas
    public Person findByEmail(String email) throws SQLException {
        String sql = "SELECT email, name, age FROM person WHERE email = ?";
        Connection connection = DBHelper.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new Person(rs.getString("email"), rs.getString("name"),rs.getInt("age"));
    }


    public void insertTransactional ( List<Person>  persons) throws PersonException{

    }


    // retourne les personnes non inserées
    public List<Person>  insertBestEffort ( List<Person> person){

    }






}
