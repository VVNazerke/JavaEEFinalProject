package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

import java.io.IOException;

@WebServlet(value = "/add-admin")
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null && currentUser.getRoleId() == 1) {
            request.getRequestDispatcher("addAdmin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null && currentUser.getRoleId() == 1) {
            String email = request.getParameter("aa-email");
            String name = request.getParameter("aa-name");
            String password = request.getParameter("aa-password");
            String repassword = request.getParameter("aa-repassword");
            if (password.equals(repassword)){
                Users user = new Users();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(name);
                user.setRoleId(1);
                DBManager.addUser(user);
                response.sendRedirect("/add-admin?success");
            }
        }else {
            response.sendRedirect("/login");
        }
    }
}
