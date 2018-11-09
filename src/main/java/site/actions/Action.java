package site.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Антон on 07.10.2018.
 */
public interface Action {
    String execute (HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
