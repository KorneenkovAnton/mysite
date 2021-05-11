package site.actions;

import DAO.DAO;
import DAO.GameDAO;
import DAO.GameDAOImpl;
import DAO.SystemRequirementsDAOImpl;
import entity.Game;
import entity.SystemRequirements;
import entity.User;
import org.apache.commons.fileupload.FileUploadException;
import pool.ConnectionPool;
import util.constants.Constants;
import util.uploader.Uploader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class AddNewGameAction implements Action, Constants {
    private final ConnectionPool pool;
    private final GameDAO<Game, User> gameDAO;
    private final DAO<SystemRequirements, SystemRequirements> systemRequirementsDAO;
    private final Uploader posterUploader;

    {
        pool = ConnectionPool.getInstance();
        gameDAO = new GameDAOImpl();
        systemRequirementsDAO = new SystemRequirementsDAOImpl();
        posterUploader = new Uploader();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute(ADDED_GAME);

        try {
            game.setPoster(posterUploader.uploadFile(request));
//            game.setPosterLink(POSTERS_FOLDER + posterUploader.uploadFile(request).getName());
            connection.setAutoCommit(false);
            systemRequirementsDAO.addToDatabase(game.getMinimalSystemRequirements(), connection);
            systemRequirementsDAO.addToDatabase(game.getRecommendedSystemRequirements(), connection);
            gameDAO.addToDatabase(game, connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException("AddNewGameAction");
        } catch (FileUploadException e) {
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
        } finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
