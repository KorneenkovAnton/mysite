package site.actions;


import DAO.AddressDAO;
import DAO.UserDAO;
import DAO.UserFriendDAO;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;


public class DeleteUserAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String answer = null;
        Connection connection = pool.getConnection();
        int idOfDelettingUser = Integer.parseInt(request.getParameter(DELETTING_USER));
        int idOfAddress = Integer.parseInt(request.getParameter(DELETTING_ADDRESS));
        User delettingUser = new User();
        delettingUser.setId(idOfDelettingUser);
        delettingUser.setAddress(new Address());
        delettingUser.getAddress().setId(idOfAddress);

        try {
            connection.setAutoCommit(false);
            new UserFriendDAO().deleteAllFriendsOfUser(delettingUser,connection);
            new UserDAO().delete(delettingUser,connection);
            connection.commit();
            answer = MAIN_PAGE_JSP_DIR;
            pool.closeConnection(connection);
        }catch (SQLException e){
            connection.rollback();
            pool.closeConnection(connection);
        }
        try {
            new AddressDAO().delete(delettingUser,connection);
        }catch (SQLException e){
            System.out.println("address is used");
        }

        return answer;
    }
}
