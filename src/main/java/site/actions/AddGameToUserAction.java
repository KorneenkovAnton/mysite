package site.actions;

import DAO.*;
import DAO.GameDAOImpl;
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
import java.util.ArrayList;
import java.util.List;


public class AddGameToUserAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        DAO gameDAO = new GameDAOImpl();
        DAO userDAO = new UserDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Game game = new Game(Integer.parseInt(request.getParameter(ADDED_GAME)));
        List<Game> userGames = new ArrayList<>();

        try {
            if(!userGames.contains(gameDAO.getById(game.getId(),connection))) {
                int gameCost = ((GameDAOImpl)gameDAO).getById(game.getId(), connection).getCost();
                if (user.getMoney() >= gameCost) {
                    connection.setAutoCommit(false);
                    ((UserDAOImpl)userDAO).buyGame(user, game, connection);
                    ((GameDAOImpl)gameDAO).addGameToUser(user, game, connection);
                    user.setMoney(user.getMoney() - gameCost);
                    connection.commit();
                    request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                } else {
                    request.setAttribute(OPERATION_STATUS, NOT_ENOUGH_MONEY);
                }
            }else {
                request.setAttribute(OPERATION_STATUS,GAME_AVAILABLE);
            }

        }catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
            throw new SQLException("AddGameToUserAction");
        }finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
