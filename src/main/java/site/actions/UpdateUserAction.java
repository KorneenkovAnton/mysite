package site.actions;

import DAO.AddressDAO;
import DAO.DAO;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.creator.AddressCreator;
import util.creator.Creator;
import util.creator.UserCreator;
import util.validator.UserValidator;
import util.validator.Validator;

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
        Creator<User> creator = new UserCreator();
        Validator<User> validator = new UserValidator();
        User userOld = (User) session.getAttribute(USER_ATTRIBUTE);
        User  user = creator.create(request);
        user.setAddress(new AddressCreator().create(request));
        user.setMoney(userOld.getMoney());
        UserDAO userDAOImpl = new UserDAOImpl();
        DAO addressDAO = new AddressDAO();

        if(user != null && validator.validate(user)){
            try {
                connection.setAutoCommit(false);
                userDAOImpl.update(user,connection);
                addressDAO.update(user,connection);
                connection.commit();
                session.setAttribute(USER_ATTRIBUTE,user);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            }
            catch (SQLException e){
                connection.rollback();
                e.printStackTrace();
                request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
                throw new SQLException("UpdateUserAction");
            }finally {
                pool.closeConnection(connection);
            }
        }else {
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
        }
        return MAIN_PAGE_DIR;
    }
}
