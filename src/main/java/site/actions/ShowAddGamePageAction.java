package site.actions;

import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Антон on 24.10.2018.
 */
public class ShowAddGamePageAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,ADD_PAGE_JSPX);
        return MAIN_PAGE_JSP_DIR;
    }
}
