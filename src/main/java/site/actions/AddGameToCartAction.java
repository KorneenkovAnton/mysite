package site.actions;

import DAO.*;
import entity.Game;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import DAO.GameDAOImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddGameToCartAction implements Action,Constants{
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        long gameID = Long.parseLong(request.getParameter(ADDED_GAME));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        DAO gameDAO = new GameDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Game game;
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);

        if(cart == null){
            cart = new ArrayList<>();
        }

        try {
            game = (Game) gameDAO.getById(gameID,connection);
            if(!cart.contains(game) && !((GameDAOImpl)gameDAO).getUserGames(user,connection).contains(game)) {
                cart.add(game);
                session.setAttribute(CART_ATTRIBUTE,cart);
            }else {
                request.setAttribute(OPERATION_STATUS,GAME_AVAILABLE);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("AddGameToCartAction");
        }finally {
            pool.closeConnection(connection);
        }

        return MAIN_PAGE_ACTION;
    }
}
