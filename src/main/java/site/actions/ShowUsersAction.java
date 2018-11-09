package site.actions;

import DAO.UserAddressJoinDAO;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class ShowUsersAction implements Action, Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null)
            currentPage =Integer.parseInt(request.getParameter(CURRENT_PAGE));
        UserAddressJoinDAO userAddressJoinDAO = new UserAddressJoinDAO();

        session.setAttribute(USERS_ATTRIBUTE,userAddressJoinDAO.getAll(connection,currentPage,3));
        request.setAttribute(CURRENT_PAGE,currentPage);
        request.setAttribute(CURRENT_ACTION_ATTRIBUTE,ALL_USERS_JSPX_DIR);
        pool.closeConnection(connection);
        return MAIN_PAGE_JSP_DIR;
    }
}
