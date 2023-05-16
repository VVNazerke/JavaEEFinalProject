package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.NewsCategory;
import model.Users;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/add-news")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws
            ServletException,
            IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            if (currentUser.getRoleId() == 1) {
                News news = new News();
                news.setTitle(request.getParameter("title"));
                news.setContent(request.getParameter("content"));

                NewsCategory newsCategory = new NewsCategory();
                Long newsCategoryId = Long.parseLong(request.getParameter("category_name"));
                newsCategory.setId(newsCategoryId);
                newsCategory.setName(DBManager.getNewsCategoryById(newsCategoryId).getName());
                news.setNewsCategory(newsCategory);
                DBManager.addNews(news);
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/page-not-found");
            }

        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            if (currentUser.getRoleId() == 1){
                ArrayList<NewsCategory> newsCategories = DBManager.getNewsCategories();
                request.setAttribute("kategorii", newsCategories);
                request.getRequestDispatcher("addNews.jsp").forward(request, response);
            }else {
                response.sendRedirect("/page-not-found");
            }

        } else {
            response.sendRedirect("/login");
        }
    }
}
