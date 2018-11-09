package site.actions;

import DAO.GameDAO;
import DAO.SystemRequirementsDAO;
import entity.Game;
import org.apache.commons.fileupload.FileUploadException;
import pool.ConnectionPool;
import util.constants.Constants;
import util.download.Uploader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class AddNewGameAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        Game game = (Game) session.getAttribute(ADDED_GAME);
        Uploader posterUploader = new Uploader();
        GameDAO gameDAO = new GameDAO();
        SystemRequirementsDAO systemRequirementsDAO = new SystemRequirementsDAO();

        try {
            game.setPosterLink(POSTERS_FOLDER + posterUploader.uploadFile(request).getName());
            connection.setAutoCommit(false);
            systemRequirementsDAO.addToDatabase(game.getMinimalSystemRequirements(),connection);
            systemRequirementsDAO.addToDatabase(game.getRecommendedSystemRequirements(),connection);
            gameDAO.addToDatabase(game,connection);
            connection.commit();

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return MAIN_PAGE_DIR;
    }
}
