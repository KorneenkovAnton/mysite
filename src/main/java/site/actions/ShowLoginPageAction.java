package site.actions;

import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowLoginPageAction implements Action,Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,LOGIN_JSP_DIR);
        return MAIN_PAGE_JSP_DIR;
    }
}
