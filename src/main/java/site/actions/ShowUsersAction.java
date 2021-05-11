package site.actions;

import DAO.*;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowUsersAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAO<User, User> userDAO;
    private final DAO<Address, User> addressDAO;

    {
        pool = ConnectionPool.getInstance();
        userDAO = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<User> users;

        try {
            users = userDAO.getAll(user, connection);
            if (users != null) {
                for (User userTemp : users) {
                    userTemp.setAddress(addressDAO.getById(userTemp.getId(), connection));
                }
            }

            session.setAttribute(USERS_ATTRIBUTE, users);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("ShowUsersAction");
        } finally {
            pool.closeConnection(connection);
        }

        return ALL_USERS_PAGE_JSP;
    }
}
