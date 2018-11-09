package site.actions;

import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Антон on 20.10.2018.
 */
public class ShowProfilePageAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,PROFILE_JSPX_DIR);
        return MAIN_PAGE_JSP_DIR;
    }
}
