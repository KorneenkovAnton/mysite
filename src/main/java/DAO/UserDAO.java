package DAO;

import entity.User;

import java.sql.*;
import java.util.List;

public class UserDAO implements DAO<User> {
    private final String INSERT_USER = "INSERT INTO user(userLogin,userPassword," +
            "dateOfBirthday,email,name,sname) VALUES (?,?,?,?,?,?)";
    private final String DELETE_USER = "DELETE FROM user WHERE " +
            "userLogin = ? AND userPassword = ?";
    private final String SELECT_BY_ID = "SELECT * FROM user WHERE userID = ";
    private final String SELECT_BY_LOGIN_AND_PASSWORD_1 = "SELECT * FROM user WHERE userLogin = ";
    private final String SELECT_BY_LOGIN_AND_PASSWORD_2 = "AND userPassword = ";
    private final String UPADTE_USER_NAME ="UPDATE user SET name = ? WHERE userID = ?";

    public void addToDatabase(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setDate(3, (Date) user.getDateOfBirthday());
        preparedStatement.setString(4,user.geteMail());
        preparedStatement.setString(5,user.getName());
        preparedStatement.setString(6,user.getsName());
        preparedStatement.executeUpdate();
    }
///????????????????????
    public void update(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement  = connection.prepareStatement(UPADTE_USER_NAME);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2, String.valueOf(user.getId()));

    }

    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
    }

    public User getById(int id, Connection connection) throws SQLException {
        User user;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id);
        if(resultSet.next()){
            user = new User();
            user.setName(resultSet.getString("name"));
            user.setsName(resultSet.getString("sname"));
            user.seteMail(resultSet.getString("email"));
            user.setDateOfBirthday(resultSet.getDate("dateOfBirthDay"));
            return user;
        }else {
            return null;
        }
    }

    public User getByLoginAndPassword(String login, String password, Connection connection) throws SQLException {
        User user;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_BY_LOGIN_AND_PASSWORD_1 + login
        + SELECT_BY_LOGIN_AND_PASSWORD_2 + password);
        if(resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("userID"));
            user.setName(resultSet.getString("name"));
            user.setsName(resultSet.getString("sname"));
            user.seteMail(resultSet.getString("email"));
            user.setDateOfBirthday(resultSet.getDate("dateOfBirthDay"));
            return user;
        }else {
            return null;
        }
    }

    public List<User> getAll(User user, Connection connection) {
        if(!user.isAdmin()){
            ////....
            throw new UnsupportedOperationException();
        }
        return null;
    }

}
