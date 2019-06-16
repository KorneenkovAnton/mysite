package site.actions;

import DAO.*;
import entity.Address;
import entity.User;
import pool.ConnectionPool;
import util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowUsersAction implements Action, Constants {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        DAO userDAO = new UserDAOImpl();
        DAO addressDAO = new AddressDAO();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        List<User> users;
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        try {
            users = ((UserDAOImpl)userDAO).getAll(user,currentPage,5,connection);
            if(users != null){
                for (User userTemp: users) {
                    user.setAddress((Address) addressDAO.getById(userTemp.getId(),connection));
                }
            }

            session.setAttribute(USERS_ATTRIBUTE, users);
            request.setAttribute(CURRENT_PAGE,currentPage);

            int countOfPages = userDAO.countOfRecords(connection,COUNT_OF_RECORDS_IN_USER_TABLE)-1;
            if(countOfPages % 5 == 0 ){
                countOfPages = countOfPages/5;
            }else {
                countOfPages= countOfPages/5+1;
            }

            request.setAttribute(COUNT_OF_PAGES_ATTRIBUTE,countOfPages);

        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("ShowUsersAction");
        }finally {
            pool.closeConnection(connection);
        }

        return ALL_USERS_PAGE_JSP;
    }
}
