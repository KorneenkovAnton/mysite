package site.actions;

import entity.Game;
import util.constants.Constants;
import util.creator.Creator;
import util.creator.GameCreator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class CreateNewGameAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Creator creator = new GameCreator();
        Game game = (Game) creator.create(request);
        session.setAttribute(ADDED_GAME,game);
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,ADD_POSTER_JSPX);

        return MAIN_PAGE_JSP_DIR;
    }
}
