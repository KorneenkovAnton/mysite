package site.actions;

import DAO.*;
import entity.Game;
import entity.Poster;
import entity.SystemRequirements;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetAllGamesAction implements Action, Constants {
    private final ConnectionPool pool;
    private final ResourceBundle rb;
    private final GameDAO<Game, User> gameDAO;
    private final DAO<SystemRequirements, SystemRequirements> systemRequirementsDAO;
    private final DAO<Poster, Poster> fileDAO;

    {
        pool = ConnectionPool.getInstance();
        rb = ResourceBundle.getBundle(PROPERTIES_NAME);
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        fileDAO = new FileDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int currentPage = 1;
        int countOfRecords = Integer.parseInt(rb.getString(COUNT_OF_RECORDS));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();

        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        try {
            List<Game> games = gameDAO.getAll(currentPage, countOfRecords, connection);
            if (games != null) {
                for (Game game : games) {
                    game.setMinimalSystemRequirements(systemRequirementsDAO.getById(
                            game.getMinimalSystemRequirements().getId(), connection));
                    game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(
                            game.getRecommendedSystemRequirements().getId(), connection));
                    game.setPoster(fileDAO.getById(game.getPoster().getId(),connection));
                }
            }

            session.setAttribute(GAMES_ATTRIBUTE, games);
            request.setAttribute(CURRENT_PAGE, currentPage);

            int countOfPages = gameDAO.countOfRecords(connection, COUNT_OF_RECORDS_IN_GAME_TABLE);
            if (countOfPages % countOfRecords == 0) {
                countOfPages = countOfPages / countOfRecords;
            } else {
                countOfPages = countOfPages / countOfRecords + 1;
            }

            request.setAttribute(COUNT_OF_PAGES_ATTRIBUTE, countOfPages);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("GetAllGamesAction");
        } finally {
            pool.closeConnection(connection);
        }
        return SHOW_GAMES_JSP;
    }
}
