package controller;

import dao.list.InMemoryUserDao;
import model.UserCreationResponse;
import model.UserInput;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = new BaseUserService(new InMemoryUserDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "message");
        req.setAttribute("email", "email");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        UserInput userInput = UserInput.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        UserCreationResponse userCreationResponse = userService.create(userInput);

        if (userCreationResponse.isSuccess()) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else  {
            req.setAttribute("message", userCreationResponse.getMessage());
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }

    }
}
