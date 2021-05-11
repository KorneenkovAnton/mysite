package site.actions;

import DAO.AddressDAOImpl;
import DAO.DAO;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.validator.UserValidator;
import util.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

import static util.validator.UserValidator.*;


public class GetUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAO<User, User> userDAO;
    private final DAO<Address, User> addressDAO;
    private final Validator<User> validator;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
        validator = new UserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        String answer = LOGIN_JSP;
        User user;

        try {
            if (validator.validateString(request.getParameter(LOGIN), LOGIN_REGEX) &&
                    validator.validateString(request.getParameter(PASSWORD), PASSWORD_REGEX)) {
                user = userDAO.getByLoginAndPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD),
                        connection);
                if (user != null) {
                    user.setAddress(addressDAO.getById(user.getId(), connection));
                    session.setAttribute(USER_ATTRIBUTE, user);
                    answer = MAIN_PAGE_ACTION;
                } else {
                    request.setAttribute(OPERATION_STATUS, WRONG_LOGIN_OR_PASSWORD);
                }
            } else {
                request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("GetUserAction");
        } finally {
            pool.closeConnection(connection);
        }

        return answer;
    }
}
