package site.actions;

import DAO.GameDAO;
import entity.Game;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteGameAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        GameDAO gameDAO = new GameDAO();
        Game game = new Game(Integer.parseInt(request.getParameter(DELERED_GAME)));

        try {
            connection.setAutoCommit(false);
            gameDAO.deleteLinks(game,connection);
            gameDAO.delete(game,connection);
            connection.commit();
            pool.closeConnection(connection);
        }catch (SQLException e){
            connection.rollback();
            pool.closeConnection(connection);
            System.out.println("the game has not been deleted");
        }
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,SHOW_GAMES);
        return MAIN_PAGE_DIR;
    }
}
