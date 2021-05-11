package site.actions;

import DAO.DAO;
import DAO.GameDAO;
import DAO.FileDAOImpl;
import DAO.GameDAOImpl;
import DAO.SystemRequirementsDAOImpl;
import DAO.CommentDAOImpl;
import DAO.CommentDAO;
import entity.*;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowGameInfoPage implements Action, Constants {
    private final ConnectionPool pool;
    private final GameDAO<Game, User> gameDAO;
    private final DAO<SystemRequirements, SystemRequirements> systemRequirementsDAO;
    private final DAO<Poster, Poster> fileDAO;
    private final CommentDAO<Comment, Comment> commentDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        fileDAO = new FileDAOImpl();
        commentDAO = new CommentDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        Long gameId = Long.valueOf(request.getParameter("gameInfo"));

        try {
            Game game = gameDAO.getById(gameId, connection);
            game.setMinimalSystemRequirements(systemRequirementsDAO.getById(
                    game.getMinimalSystemRequirements().getId(), connection));
            game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(
                    game.getRecommendedSystemRequirements().getId(), connection));
            game.setPoster(fileDAO.getById(game.getPoster().getId(),connection));
            game.setComments(commentDAO.getByGame(gameId, connection));

            session.setAttribute("gameInfo", game);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Get game info");
        } finally {
            pool.closeConnection(connection);
        }


        return SHOW_GAME_INFO_JSP;
    }
}
