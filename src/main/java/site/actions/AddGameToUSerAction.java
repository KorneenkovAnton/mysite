package site.actions;

import DAO.GameDAO;
import entity.Game;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;


public class AddGameToUSerAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        GameDAO gameDAO = new GameDAO();
        int userID = Integer.parseInt(request.getParameter(USERID_COLUMN));
        int gameID = Integer.parseInt(request.getParameter(ADDED_GAME));

        try {
            gameDAO.addGameToUser(new User(userID),new Game(gameID), connection);
            pool.closeConnection(connection);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("game hasn't been added");
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_DIR;
    }
}
