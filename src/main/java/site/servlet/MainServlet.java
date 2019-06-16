package site.servlet;

import site.actions.AbstractActionFactory;
import site.actions.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = AbstractActionFactory.getInstance().getAction(req,resp);
        if(action != null){
            String view = null;
            try {
                view = action.execute(req,resp);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher(view).forward(req,resp);
        }else{
            resp.getWriter().write("Error"+ req.getServletPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
