package site.actions;

import DAO.*;
import entity.Game;
import entity.Poster;
import entity.SystemRequirements;
import entity.User;
import javafx.geometry.Pos;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ShowMyGamesAction implements Action, Constants {
    private final ConnectionPool pool;
    private final GameDAO<Game, User> gameDAO;
    private final DAO<Poster, Poster> fileDAO;
    private final DAO<SystemRequirements, SystemRequirements> systemRequirementsDAO;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        fileDAO = new FileDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try {
            user.setOwnedGames(gameDAO.getUserGames(user, connection)
                    .stream()
                    .peek(game -> {
                        try {
                            game.setPoster(fileDAO.getById(game.getPoster().getId(), connection));
                            game.setMinimalSystemRequirements(systemRequirementsDAO.getById(
                                    game.getMinimalSystemRequirements().getId(),connection));
                            game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(
                                    game.getRecommendedSystemRequirements().getId(),connection));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    })
                    .collect(Collectors.toList()));
            session.setAttribute(USER_ATTRIBUTE, user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("could not find games" + user.getId());
        } finally {
            pool.closeConnection(connection);
        }
        session.setAttribute(USER_ATTRIBUTE, user);

        return SHOW_MY_GAMES_PAGE_JSP;
    }
}
