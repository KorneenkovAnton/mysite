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

public class MyFriendsAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        UserFriendDAO userFriendDAO = new UserFriendDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try{
            user.setFriends(userFriendDAO.selectAllFriendsOfUser(user,connection));
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("MyFriendsAction");
        }finally {
            pool.closeConnection(connection);
        }

        session.setAttribute(USER_ATTRIBUTE,user);
        request.setAttribute(STATUS,1);

        return USER_FRIENDS_JSP;
    }
}
