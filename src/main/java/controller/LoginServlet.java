package controller;

import dao.list.InMemoryUserDao;
import model.LoginStatus;
import org.apache.log4j.Logger;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new BaseUserService(new InMemoryUserDao());
    private Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        LoginStatus loginStatus = userService.authenticate(email, password);

        switch (loginStatus) {
            case USER_NOT_FOUND: {
                LOGGER.warn("User not found in DB");
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
                break;
            }
            case INCORRECT_PASSWORD: {
                LOGGER.warn(String.format("User entered incorrect password. Email = %s" , email));
                doGet(req, resp);
                break;
            }
            case AUTHENTICATED: {
                LOGGER.info(String.format("User successfully logged. Email = %s" , email));
                req.setAttribute("email", email);
                req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
                break;
            }
        }
    }
}
