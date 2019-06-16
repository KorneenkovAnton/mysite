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


public class SetAdminAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        DAO userDAO = new UserDAOImpl();
        User admin = (User) session.getAttribute(USER_ATTRIBUTE);
        int newAdminID = Integer.parseInt(request.getParameter(NEW_ADMIN_ID));
        User newAdmin = new User(newAdminID);

        if(admin.isAdmin()){
            try {
                ((UserDAOImpl)userDAO).setAdmin(newAdmin,connection);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            }catch (SQLException e){
                e.printStackTrace();
                request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
                throw new SQLException("SetAdminAction");
            }finally {
                pool.closeConnection(connection);
            }
        }

        return MAIN_PAGE_ACTION;
    }
}
