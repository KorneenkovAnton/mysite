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
import java.util.List;

public class AddGameToUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final GameDAO<Game, User> gameDAO;
    private final UserDAO<User, User> userDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);

        try {
            user.setOwnedGames(gameDAO.getUserGames(user, connection));
            Game game = gameDAO.getById(Integer.parseInt(request.getParameter(ADDED_GAME)), connection);
            if ((user.getOwnedGames() == null || !user.getOwnedGames().contains(game)) &&
                    (cart == null || !cart.contains(game))) {
                if (user.getMoney() >= game.getCost()) {
                    connection.setAutoCommit(false);
                    userDAO.buyGame(user, game, connection);
                    gameDAO.addGameToUser(user, game, connection);
                    user.setMoney(user.getMoney() - game.getCost());
                    connection.commit();
                    request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                } else {
                    request.setAttribute(OPERATION_STATUS, NOT_ENOUGH_MONEY);
                }
            } else {
                request.setAttribute(OPERATION_STATUS, GAME_AVAILABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException("AddGameToUserAction");
        } finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
