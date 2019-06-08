package site.actions;

import DAO.GameDAO;
import DAO.GameDAOImpl;
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
        GameDAO gameDAO = new GameDAOImpl();
        Game game = new Game(Integer.parseInt(request.getParameter(DELERED_GAME)));

        try {
            connection.setAutoCommit(false);
            gameDAO.deleteLinks(game,connection);
            gameDAO.delete(game,connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        }catch (SQLException e){
            connection.rollback();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw new SQLException("DeleteGameAction");
        }finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_DIR;
    }
}
