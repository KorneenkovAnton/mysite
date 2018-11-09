package site.actions;

import DAO.AddressDAO;
import DAO.UserDAO;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class GetUserAction implements Action,Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Connection connection = pool.getConnection();
        UserDAO userDAO = new UserDAO();
        AddressDAO addressDAO = new AddressDAO();
        String answer = "/Login.jsp";
        try {
            User user = userDAO.getByLoginAndPassword(request.getParameter(LOGIN), request.getParameter(PASSWORD),
                    connection);
            if (user != null) {
                user.setAddress(addressDAO.getById(user.getId(), connection));


                session.setAttribute(USER_ATTRIBUTE, user);


                answer = MAIN_PAGE_DIR;
            }
        }catch (SQLException e){
            pool.closeConnection(connection);
            throw new SQLException();
        }
        pool.closeConnection(connection);
        return answer;
    }
}
