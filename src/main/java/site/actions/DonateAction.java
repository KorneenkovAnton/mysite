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


public class DonateAction implements Action,Constants {
    private  final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int money = Integer.parseInt(request.getParameter(DONATE_ATTRIBUTE));
        Connection connection = pool.getConnection();
        HttpSession session = request.getSession();
        DAO userDAO = new UserDAOImpl();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        try {
            ((UserDAOImpl)userDAO).donate(user,money,connection);
            user.setMoney(user.getMoney() + money);
            session.setAttribute(USER_ATTRIBUTE,user);
            request.setAttribute(OPERATION_STATUS, OPERATION_SUCCESS);
        }catch (SQLException e){
            e.printStackTrace();
            request.setAttribute(OPERATION_STATUS,OPERATION_ERROR);
        }finally {
            pool.closeConnection(connection);
        }
        return MAIN_PAGE_ACTION;
    }
}
