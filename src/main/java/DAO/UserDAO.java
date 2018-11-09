package DAO;

import entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import util.constants.Constants;

import java.sql.*;


public class UserDAO implements DAO<User,User>,Constants {



    public void addToDatabase(User user, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER,PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, DigestUtils.md2Hex(user.getPassword()));
        preparedStatement.setDate(3, (Date) user.getDateOfBirthday());
        preparedStatement.setString(4, user.geteMail());
        preparedStatement.setString(5, user.getName());
        preparedStatement.setString(6, user.getsName());
        preparedStatement.setLong(7,user.getAddress().getId());
        preparedStatement.execute();
        try(ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
            if(generatedKey.next()){
                user.setId(generatedKey.getInt(1));
                closePrepareStatement(preparedStatement);
            }
            else {
                closePrepareStatement(preparedStatement);
                throw new SQLException("Creating user failed");
            }
        }
    }


    public void update(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement  = connection.prepareStatement(UPADTE_USER);

        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getsName());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setDate(4, (Date) user.getDateOfBirthday());
        preparedStatement.setString(5,user.geteMail());
        preparedStatement.setLong(6, user.getId());

        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user.userID = ?");
        preparedStatement.setLong(1,user.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    public User getById(long id, Connection connection) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            user = new User();
            user.setName(resultSet.getString(NAME_COLUMN));
            user.setsName(resultSet.getString(SNAME_COLUMN));
            user.seteMail(resultSet.getString(EMAIL_COLUMN));
            user.setDateOfBirthday(resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN));
            closePrepareStatement(preparedStatement);

        }else {
            closePrepareStatement(preparedStatement);
        }
        return user;
    }


    public User getByLoginAndPassword(String login, String password, Connection connection) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement =connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,DigestUtils.md2Hex(password));
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            user = new User(resultSet.getBoolean(ADMIN_COLUMN));
            user.setLogin(login);
            user.setPassword(password);
            user.setId(resultSet.getInt(USERID_COLUMN));
            user.setName(resultSet.getString(NAME_COLUMN));
            user.setsName(resultSet.getString(SNAME_COLUMN));
            user.seteMail(resultSet.getString(EMAIL_COLUMN));
            user.setDateOfBirthday(resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN));
        }
        closePrepareStatement(preparedStatement);
        return user;
    }


   /* public List<User> getAll(Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
        /*PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        List<User> users = null;
        User userTemp = new User();
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            users = new ArrayList<>();
            do {
                userTemp.setId(resultSet.getInt(USERID_COLUMN));
                userTemp.setDateOfBirthday(resultSet.getDate(DATE_OF_BIRTHDAY_COLUMN));
                userTemp.seteMail(resultSet.getString(EMAIL_COLUMN));
                userTemp.setName(resultSet.getString(NAME_COLUMN));
                userTemp.setsName(resultSet.getString(SNAME_COLUMN));
                users.add(userTemp);

            } while (resultSet.next());
        }
        closePrepareStatement(preparedStatement);
        return users;
    }*/
}
