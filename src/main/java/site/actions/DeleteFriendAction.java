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

public class DeleteFriendAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        UserFriendDAO userFriendDAO = new UserFriendDAO();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int friendID = Integer.parseInt(request.getParameter("friendID"));

        try {
            userFriendDAO.deleteFriend(user,friendID,connection);
            pool.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
            pool.closeConnection(connection);
            throw new SQLException("friend wasn't delete");
        }
        return MAIN_PAGE_DIR;
    }
}
