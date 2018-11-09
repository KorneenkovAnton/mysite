package site.actions;

import DAO.GameDAO;
import DAO.SystemRequirementsDAO;
import entity.Game;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetAllGamesAction implements Action,Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        GameDAO gameDAO = new GameDAO();
        SystemRequirementsDAO systemRequirementsDAO = new SystemRequirementsDAO();
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null)
            currentPage =Integer.parseInt(request.getParameter(CURRENT_PAGE));

        List<Game> games = gameDAO.getAll(currentPage,5,connection);
        if(games !=null) {
            for (Game game : games) {
                game.setMinimalSystemRequirements(systemRequirementsDAO.getById(game.getMinimalSystemRequirements().getId(), connection));
                game.setRecommendedSystemRequirements(systemRequirementsDAO.getById(game.getRecommendedSystemRequirements().getId(), connection));
            }
        }
        session.setAttribute(GAMES_ATTRIBUTE,games);
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,SHOW_GAMES);

        request.setAttribute(CURRENT_PAGE,currentPage);
        pool.closeConnection(connection);
        
        return MAIN_PAGE_JSP_DIR;
    }
}
