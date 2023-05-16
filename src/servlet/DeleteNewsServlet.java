package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.Users;

import java.io.IOException;

@WebServlet("/delete-news")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users users = (Users) request.getSession().getAttribute("currentUser");
        if (users != null && users.getRoleId() == 1) {
            Long news_id = Long.valueOf(request.getParameter("news_id"));
            System.out.println("news id: " + news_id);
            News news = DBManager.getNewsById(news_id);
            DBManager.deleteComments(news_id);
            DBManager.deleteNews(news);

            response.sendRedirect("/");
        } else {
            response.sendRedirect("/page-not-found");
        }
    }
}
