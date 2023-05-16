package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute("currentUser");
        if (currentUser != null) {
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("user_password");
        String newPassword = request.getParameter("user_new_password");
        Users user = DBManager.getUser(request.getParameter("user-email"));

        String redirect = "/profile?password-success";
        if (oldPassword.equals(user.getPassword())) {
            DBManager.changePassword(user, newPassword);
        } else {
            redirect = "/profile?password-error";
        }

        response.sendRedirect(redirect);
    }
}
