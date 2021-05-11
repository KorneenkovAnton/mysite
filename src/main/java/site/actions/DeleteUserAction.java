package site.actions;


import DAO.*;
import entity.Address;
import entity.Game;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;


public class DeleteUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserFriendDAO userFriendDAO;
    private final GameDAO<Game, User> gameDAO;
    private final UserDAO<User, User> userDAO;

    {
        pool = ConnectionPool.getInstance();
        userFriendDAO = new UserFriendDAOImpl();
        gameDAO = new GameDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int idOfDeletedUser = Integer.parseInt(request.getParameter(DELETED_USER));
        int idOfAddress = Integer.parseInt(request.getParameter(DELETED_ADDRESS));
        Connection connection = pool.getConnection();
        User deletedUser = new User();

        deletedUser.setId(idOfDeletedUser);
        deletedUser.setAddress(new Address());
        deletedUser.getAddress().setId(idOfAddress);

        try {
            connection.setAutoCommit(false);
            userFriendDAO.deleteAllFriendsOfUser(deletedUser, connection);
            gameDAO.deleteAllUserGames(deletedUser, connection);
            userDAO.delete(deletedUser, connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException("DeleteUserAction");
        }
        try {
            new AddressDAOImpl().delete(deletedUser, connection);
        } catch (SQLException e) {
            System.out.println("address is used");
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
