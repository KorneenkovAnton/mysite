package site.actions;

import entity.Game;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class DeleteFromCartAction implements Action,Constants{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        List<Game> cart = (List<Game>) session.getAttribute(CART_ATTRIBUTE);
        long gameId = Long.parseLong(request.getParameter("deleteCart"));
        cart.removeIf(n->(n.getId() == gameId)
        );
        session.setAttribute(CART_ATTRIBUTE,cart);
        return CART_JSP;
    }
}
