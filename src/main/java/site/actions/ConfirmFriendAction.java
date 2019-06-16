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


public class ConfirmFriendAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        UserFriendDAO userFriendDAO = new UserFriendDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        int friendID = Integer.parseInt(request.getParameter(FRIEND_ID));

        try {
            userFriendDAO.confirmRel(user,friendID,connection);
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        }catch (SQLException e){
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw new SQLException("ConfirmFriendAction");
        }finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
