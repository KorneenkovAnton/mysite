package site.actions;

import DAO.*;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowMyGamesAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        DAO gameDAO = new GameDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try {
            user.setOwnedGames(((GameDAOImpl)gameDAO).getUserGames(user,connection));
            session.setAttribute(USER_ATTRIBUTE, user);
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("could not find games"+user.getId());
        }finally {
            pool.closeConnection(connection);
        }
        session.setAttribute(USER_ATTRIBUTE,user);

        return SHOW_MY_GAMES_PAGE_JSP;
    }
}
