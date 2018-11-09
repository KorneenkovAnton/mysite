package site.actions;

import DAO.*;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.creator.Creator;
import util.creator.UserCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterUserAction implements Action, Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        Creator creator = new UserCreator();
        User user = (User) creator.create(request);
        AddressDAO addressDAO = new AddressDAO();
        UserDAO userDAO = new UserDAO();

        request.setAttribute(CURRENT_ACTION_ATTRIBUTE , REGISTER_PAGE_JSPX);

        try {
            connection.setAutoCommit(false);
            addressDAO.addToDatabase(user,connection);
            userDAO.addToDatabase(user,connection);
            connection.commit();
            pool.closeConnection(connection);
            request.setAttribute(CURRENT_ACTION_ATTRIBUTE,LOGIN_JSP_DIR);
        }catch (SQLException e){
            connection.rollback();
            pool.closeConnection(connection);
            e.printStackTrace();
        }
        return MAIN_PAGE_JSP_DIR;
    }
}
