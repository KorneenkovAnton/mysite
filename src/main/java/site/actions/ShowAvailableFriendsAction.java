package site.actions;

import DAO.UserFriendDAO;
import DAO.UserFriendDAOImpl;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowAvailableFriendsAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserFriendDAO userFriendDAO;

    {
        pool = ConnectionPool.getInstance();
        userFriendDAO = new UserFriendDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try {
            session.setAttribute("availableFriends", userFriendDAO.getAllAvailableFriends(user,connection));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Available Friends");
        } finally {
            pool.closeConnection(connection);
        }

        return USER_AVAILABLE_FRIENDS_JSP;
    }
}
