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
        DAO addressDAO = new AddressDAO();
        UserDAO userDAOImpl = new UserDAOImpl();
        String answer = REGISTER_PAGE_JSP;

        try {
            connection.setAutoCommit(false);
            addressDAO.addToDatabase(user,connection);
            userDAOImpl.addToDatabase(user,connection);
            connection.commit();
            answer = LOGIN_JSP;
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        }catch (SQLException e){
            connection.rollback();
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
            throw new SQLException("RegisterUserAction");
        }finally {
            pool.closeConnection(connection);
        }
        return answer;
    }
}
