import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

Logger logger = LogManager.getLogger();
void main() {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","password")) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM person");
        ResultSet persons = statement.executeQuery();
        while(persons.next()){
            String name = persons.getString("name");
            String firstName = persons.getString("first_name");
            String email = persons.getString("email");
            System.out.println(name+" "+firstName+" "+email);
        }
    } catch (SQLException e) {
        logger.error("An error occurred while connecting to the database\n" + e.getMessage());
        throw new RuntimeException(e);
    }
}