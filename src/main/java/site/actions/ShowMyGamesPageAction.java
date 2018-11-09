package site.actions;

import DAO.GameDAO;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowMyGamesPageAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        GameDAO gameDAO = new GameDAO();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        try {
            user.setOwnedGames(gameDAO.getUserGames(user,connection));
            pool.closeConnection(connection);
        }catch (SQLException e){
            pool.closeConnection(connection);
            throw new SQLException("could not find games"+user.getId());
        }
        session.setAttribute(USER_ATTRIBUTE,user);
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,SHOW_GAMES);
        return MAIN_PAGE_JSP_DIR;
    }
}
