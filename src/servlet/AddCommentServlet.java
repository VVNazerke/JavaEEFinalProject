package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comments;
import model.News;
import model.Users;

import java.io.IOException;

@WebServlet(value = "/comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws
            ServletException,
            IOException {
        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        if (currentUser!=null) {
            Comments comments = new Comments();
            String commentText = request.getParameter("comment_text");
            comments.setComment(commentText);

            Users user = DBManager.getUser(request.getParameter("user_email"));
            comments.setUser(user);

            Long newsId = Long.valueOf(request.getParameter("news_id_c"));
            News news = DBManager.getNewsById(newsId);
            comments.setNews(news);
            DBManager.addComment(comments);
            response.sendRedirect("/details?news_id=" + newsId);
        }else {
            response.sendRedirect("/login");
        }
    }
}
