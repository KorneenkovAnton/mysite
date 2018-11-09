package site.actions;

import DAO.UserFriendDAO;
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
        UserFriendDAO userFriendDAO = new UserFriendDAO();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int status = Integer.parseInt(request.getParameter("status"));

        try{
            user.setFriends(userFriendDAO.selectAllFriendsOfUser(user,connection,status));
            pool.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
            pool.closeConnection(connection);
            throw new SQLException("from MyFriendsAction");
        }

        request.setAttribute(USER_ATTRIBUTE,user);
        request.setAttribute("status",status);
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE, USER_FRIENDS_JSPX);
        return MAIN_PAGE_JSP_DIR;
    }
}
