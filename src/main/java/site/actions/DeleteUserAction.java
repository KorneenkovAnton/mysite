package site.actions;


import DAO.*;
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
        Connection connection = pool.getConnection();
        int idOfDeletedUser = Integer.parseInt(request.getParameter(DELETED_USER));
        int idOfAddress = Integer.parseInt(request.getParameter(DELETED_ADDRESS));
        UserFriendDAO userFriendDAO = new UserFriendDAOImpl();
        DAO gameDAO = new GameDAOImpl();
        DAO userDAO = new UserDAOImpl();
        User deletedUser = new User();
        deletedUser.setId(idOfDeletedUser);
        deletedUser.setAddress(new Address());
        deletedUser.getAddress().setId(idOfAddress);

        try {
            connection.setAutoCommit(false);
            userFriendDAO.deleteAllFriendsOfUser(deletedUser,connection);
            ((GameDAOImpl)gameDAO).deleteAllUserGames(deletedUser,connection);
            userDAO.delete(deletedUser,connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        }catch (SQLException e){
            connection.rollback();
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw new SQLException("DeleteUserAction");
        }
        try {
            new AddressDAO().delete(deletedUser,connection);
        }catch (SQLException e){
            System.out.println("address is used");
        }finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
