package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet(value = "/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String redirect = "/sign-up?email-error";
        String email = request.getParameter("su-email");
        String password = request.getParameter("su-password");
        String repassword = request.getParameter("su-repassword");
        String fullName = request.getParameter("su-first-name") + " " + request.getParameter("su-second-name");
        Users userExists = DBManager.getUser(email);
        if (userExists == null) {
            redirect = "/sign-up?password-error";
            if (password.equals(repassword)) {
                redirect = "/sign-up?success";
                Users user = new Users();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(fullName);
                DBManager.addUser(user);
            }
        }

        response.sendRedirect(redirect);

    }
}
