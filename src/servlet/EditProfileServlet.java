package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet(value = "/edit-profile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            String fullName = request.getParameter("full_name");
            Users user = (Users) request.getSession().getAttribute("currentUser");
            user.setFullName(fullName);
            DBManager.editProfile(user);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
