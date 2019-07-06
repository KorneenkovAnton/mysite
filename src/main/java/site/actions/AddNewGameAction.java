package site.actions;

import DAO.DAO;
import DAO.GameDAOImpl;
import DAO.SystemRequirementsDAO;
import entity.Game;
import org.apache.commons.fileupload.FileUploadException;
import pool.ConnectionPool;
import util.constants.Constants;
import util.uploader.Uploader;

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
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        DAO gameDAO = new GameDAOImpl();
        DAO systemRequirementsDAO = new SystemRequirementsDAO();
        Game game = (Game) session.getAttribute(ADDED_GAME);
        Uploader posterUploader = new Uploader();

        try {
            game.setPosterLink(POSTERS_FOLDER + posterUploader.uploadFile(request).getName());
            connection.setAutoCommit(false);
            systemRequirementsDAO.addToDatabase(game.getMinimalSystemRequirements(),connection);
            systemRequirementsDAO.addToDatabase(game.getRecommendedSystemRequirements(),connection);
            gameDAO.addToDatabase(game,connection);
            connection.commit();
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);

        }catch (SQLException e){
          e.printStackTrace();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
          throw new SQLException("AddNewGameAction");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
