package site.actions;

import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Антон on 09.03.2019.
 */
public class ShowDonatePageAction implements Action, Constants {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return DONATE_JSP;
    }
}
