import DAO.UserDAO;
import entity.User;
import pool.ConnectionPool;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        user.setLogin("korneenkov");
        user.setPassword("1234");
        user.setDateOfBirthday(new Date(new java.util.Date().getTime()));
        user.seteMail("sada");
        user.setName("sad");
        user.setsName("adas");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlinfo");
        System.out.println(resourceBundle.getKeys().hasMoreElements());

        try {
            userDAO.addToDatabase(user,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
