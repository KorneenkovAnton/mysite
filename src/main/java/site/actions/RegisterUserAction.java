package site.actions;

import DAO.*;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.creator.Creator;
import util.creator.UserCreator;
import util.validator.AddressValidator;
import util.validator.UserValidator;
import util.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final Creator<User> creator;
    private final DAO<Address, User> addressDAOImpl;
    private final UserDAO<User, User> userDAOImpl;
    private final Validator<Address> addressValidator;
    private final Validator<User> userValidator;

    {
        pool = ConnectionPool.getInstance();
        creator = new UserCreator();
        addressDAOImpl = new AddressDAOImpl();
        userDAOImpl = new UserDAOImpl();
        addressValidator = new AddressValidator();
        userValidator = new UserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        User user = creator.create(request);
        String answer = REGISTER_PAGE_JSP;

        try {

            if (addressValidator.isValid(user.getAddress()) && userValidator.isValid(user)) {
                connection.setAutoCommit(false);
                addressDAOImpl.addToDatabase(user, connection);
                userDAOImpl.addToDatabase(user, connection);
                answer = LOGIN_JSP;
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                connection.commit();
            } else {
                request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
            }

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException("RegisterUserAction");
        } finally {
            pool.closeConnection(connection);
        }
        return answer;
    }
}
