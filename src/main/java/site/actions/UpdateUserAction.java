package site.actions;

import DAO.AddressDAOImpl;
import DAO.DAO;
import DAO.UserDAO;
import DAO.UserDAOImpl;
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
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateUserAction implements Action, Constants {
    private final ConnectionPool pool;
    private final UserDAO<User, User> userDAOImpl;
    private final DAO<Address, User> addressDAO;
    private final Creator<User> creator;
    private final Validator<User> userValidator;
    private final Validator<Address> addressValidator;

    {
        pool = ConnectionPool.getInstance();
        userDAOImpl = new UserDAOImpl();
        addressDAO = new AddressDAOImpl();
        creator = new UserCreator();
        userValidator = new UserValidator();
        addressValidator = new AddressValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        User userUpdate = creator.create(request);

        userUpdate.setMoney(currentUser.getMoney());
        userUpdate.setId(currentUser.getId());

        if (userValidator.isValid(userUpdate) && addressValidator.isValid(userUpdate.getAddress())) {
            try {
                connection.setAutoCommit(false);
                userDAOImpl.update(userUpdate, connection);
                addressDAO.update(userUpdate, connection);
                connection.commit();
                session.setAttribute(USER_ATTRIBUTE, userUpdate);
                request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                request.setAttribute(OPERATION_STATUS, OPERATION_ERROR);
                throw new SQLException("UpdateUserAction");
            } finally {
                pool.closeConnection(connection);
            }
        } else {
            request.setAttribute(OPERATION_STATUS, VALIDATION_ERROR);
        }
        return MAIN_PAGE_ACTION;
    }
}
