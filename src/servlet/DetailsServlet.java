package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comments;
import model.News;
import model.NewsCategory;
import model.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser!=null) {
            News news = DBManager.getNewsById(Long.parseLong(request.getParameter("news_id")));
            request.setAttribute("novost", news);
            ArrayList<NewsCategory> newsCategories = DBManager.getNewsCategories();
            request.setAttribute("kategorii", newsCategories);
            int commentsCount = DBManager.commentsCount(news.getId());
            request.setAttribute("commCount", commentsCount);
            ArrayList<Comments> comments = DBManager.commentsOfNews(news.getId());
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("details.jsp").forward(request, response);
        }else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            Long id = Long.valueOf(request.getParameter("news_id"));
            Long category_id = Long.valueOf(request.getParameter("re_category_id"));
            System.out.println(category_id);
            String title = request.getParameter("re_title");
            String content = request.getParameter("re_content");
            News news = DBManager.getNewsById(id);
            news.getNewsCategory().setId(category_id);
            news.setTitle(title);
            news.setContent(content);
            DBManager.editNews(news);

            response.sendRedirect("/details?news_id=" + id);
        } else {
            response.sendRedirect("/login");
        }
    }
}
