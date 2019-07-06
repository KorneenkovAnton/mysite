package site.servlet;

import org.apache.log4j.Logger;
import site.actions.AbstractActionFactory;
import site.actions.Action;
import util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet implements Constants {

    private static  final Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = AbstractActionFactory.getInstance().getAction(req,resp);
        if(action != null){
            String view;
            try {
                view = action.execute(req,resp);
                getServletContext().getRequestDispatcher(view).forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                req.setAttribute(HTTPError, ERROR_505);
                getServletContext().getRequestDispatcher(ERROR_JSP).forward(req,resp);
            }
        }else{
            req.setAttribute(HTTPError, ERROR_404);
            getServletContext().getRequestDispatcher(ERROR_JSP).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
