package site.actions;

import DAO.DAO;
import DAO.GameDAO;
import DAO.GameDAOImpl;
import DAO.SystemRequirementsDAO;
import entity.Game;
import entity.SystemRequirements;
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
        GameDAO gameDAO = new GameDAOImpl();
        DAO systemRequirementsDAO = new SystemRequirementsDAO();
        int currentPage = 1;

        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        try {
            List<Game> games = gameDAO.getAll(currentPage,5,connection);
            if(games !=null) {
                for (Game game : games) {
                    game.setMinimalSystemRequirements((SystemRequirements) systemRequirementsDAO.getById(
                            game.getMinimalSystemRequirements().getId(), connection));
                    game.setRecommendedSystemRequirements((SystemRequirements) systemRequirementsDAO.getById(
                            game.getRecommendedSystemRequirements().getId(), connection));
                }
            }

            session.setAttribute(GAMES_ATTRIBUTE,games);
            request.setAttribute(CURRENT_PAGE,currentPage);

            int countOfPages = gameDAO.countOfRecords(connection,COUNT_OF_RECORDS_IN_GAME_TABLE);
            if(countOfPages % 5 == 0 ){
                countOfPages = countOfPages/5;
            }else {
                countOfPages= countOfPages/5+1;
            }

            request.setAttribute(COUNT_OF_PAGES_ATTRIBUTE,countOfPages);

        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("GetAllGamesAction");
        }finally {
            pool.closeConnection(connection);
        }
        return SHOW_GAMES_JSP;
    }
}
