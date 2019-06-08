package site.actions;

import DAO.GameDAO;
import DAO.GameDAOImpl;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import entity.Game;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class AddGameToUserAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        GameDAO<Game,User> gameDAO = new GameDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Game game = new Game(Integer.parseInt(request.getParameter(ADDED_GAME)));

        try {
            connection.setAutoCommit(false);

            int gameCost = gameDAO.getById(game.getId(),connection).getCost();
            if(user.getMoney() >= gameCost){
                userDAO.byGame( user, game,connection);
                gameDAO.addGameToUser(user,game, connection);
                user.setMoney(user.getMoney() - gameCost);
                connection.commit();
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            }
            else {
                request.setAttribute(OPERATION_STATUS,NOT_ENOUGH_MONEY);
            }
        }catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw new SQLException("AddGameToUserAction");
        }finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_DIR;
    }
}
