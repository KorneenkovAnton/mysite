package site.actions;

import DAO.AddressDAO;
import DAO.UserDAO;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.creator.AddressCreator;
import util.creator.Creator;
import util.creator.UserCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class UpdateUserAction implements Action, Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        Creator creator = new UserCreator();
        User  user = (User) creator.create(request);
        user.setAddress(new AddressCreator().create(request));
        UserDAO userDAO = new UserDAO();
        AddressDAO addressDAO = new AddressDAO();
        if(user != null){
            try {
                connection.setAutoCommit(false);
                userDAO.update(user,connection);
                addressDAO.update(user,connection);
                connection.commit();
                pool.closeConnection(connection);
                session.setAttribute(USER_ATTRIBUTE,user);
            }
            catch (SQLException e){
                connection.rollback();
                pool.closeConnection(connection);
            }
        }
        return MAIN_PAGE_DIR;
    }
}
