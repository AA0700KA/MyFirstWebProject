package finalproject.servlets;


import finalproject.wrappers.IRequestWrapper;
import finalproject.wrappers.MainRequestWrapper;
import finalproject.command.Command;
import finalproject.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Class Controller
 * Main controller that get parameter on post or get request and genrate Command
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       proccessRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       proccessRequest(request, response);
    }

    private void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IRequestWrapper wrapper = new MainRequestWrapper(request);
        String language = (String) wrapper.getSession().getAttribute("language");

        if (language == null) {
            language = "en-EN";
        }

        Locale.setDefault(new Locale(language.split("-")[0], language.split("-")[1]));
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.getCommand(wrapper);
        String pageView = command.execute(wrapper, false);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageView);
        dispatcher.forward(request, response);
    }

}
