package site.actions;

import DAO.*;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.creator.Creator;
import util.creator.UserCreator;
import util.validator.AddressValidator;
import util.validator.UserValidator;
import util.validator.Validator;

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
        DAO userDAOImpl = new UserDAOImpl();
        Validator<Address> addressValidator = new AddressValidator();
        Validator<User> userValidator = new UserValidator();
        String answer = REGISTER_PAGE_JSP;

        try {

            if(addressValidator.validate(user.getAddress()) && userValidator.validate(user)) {
                connection.setAutoCommit(false);
                addressDAO.addToDatabase(user, connection);
                userDAOImpl.addToDatabase(user, connection);
                answer = LOGIN_JSP;
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
                connection.commit();
            }

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
