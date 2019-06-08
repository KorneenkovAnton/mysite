package site.actions;

import DAO.AddressDAO;
import DAO.DAO;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;
import util.validator.UserValidator;
import util.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

import static util.validator.UserValidator.*;


public class GetUserAction implements Action,Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        UserDAO userDAO = new UserDAOImpl();
        DAO addressDAO = new AddressDAO();
        Validator validator = new UserValidator();
        String answer = LOGIN_JSP;
        try {
            User user = null;
            if(validator.validateString(request.getParameter(LOGIN),LOGIN_REGEX) &&
                    validator.validateString(request.getParameter(PASSWORD),PASSWORD_REGEX)) {
                user = (User) userDAO.getByLoginAndPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD),
                        connection);
            }
            if (user != null) {
                user.setAddress((Address) addressDAO.getById(user.getId(), connection));
                session.setAttribute(USER_ATTRIBUTE, user);
                answer = MAIN_PAGE_DIR;
            }
        }catch (SQLException e){
            throw new SQLException();
        }finally {
            pool.closeConnection(connection);
        }

        return answer;
    }
}
