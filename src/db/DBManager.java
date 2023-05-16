package db;

import model.Comments;
import model.News;
import model.NewsCategory;
import model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sprint_task", "root", "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getAllNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id as news_id, n.post_date, n.title, n.content, nc.id as nc_id, nc.name FROM news as n " +
                            "JOIN news_categories as nc " +
                            "ON n.category_id = nc.id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News newses = new News();
                newses.setId(resultSet.getLong("news_id"));
                newses.setPostDate(resultSet.getTimestamp("post_date"));
                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getLong("nc_id"));
                newsCategory.setName(resultSet.getString("name"));
                newses.setNewsCategory(newsCategory);
                newses.setTitle(resultSet.getString("title"));
                newses.setContent(resultSet.getString("content"));
                news.add(newses);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static ArrayList<NewsCategory> getNewsCategories() {
        ArrayList<NewsCategory> newsCategories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getLong("id"));
                newsCategory.setName(resultSet.getString("name"));
                newsCategories.add(newsCategory);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategories;
    }

    public static NewsCategory getNewsCategoryById(Long id) {
        NewsCategory newsCategory = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories WHERE id=?"
            );
            statement.setInt(1, Integer.parseInt(id.toString()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getLong("id"));
                newsCategory.setName(resultSet.getString("name"));
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategory;
    }

    public static void addNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (post_date, category_id, title, content) " +
                            "VALUES (NOW(),?,?,?)"
            );
            statement.setLong(1, news.getNewsCategory().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static News getNewsById(Long id) {
        News news = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.title, n.content, nc.id as nc_id, nc.name as nc_name " +
                            "FROM news n " +
                            "JOIN news_categories nc ON n.category_id=nc.id " +
                            "WHERE n.id=?"
            );
            statement.setInt(1, Integer.parseInt(id.toString()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                news = new News();
                news.setId(id);
                news.setPostDate(resultSet.getTimestamp("post_date"));
                NewsCategory newsCategory = new NewsCategory(resultSet.getLong("nc_id"),
                        resultSet.getString("nc_name"));
                news.setNewsCategory(newsCategory);
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void editNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE news " +
                            "SET category_id=?, title=?, content=? " +
                            "WHERE id=?"
            );
            statement.setLong(1, news.getNewsCategory().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setLong(4, news.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Users user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (email, password, full_name, role_id) " +
                            "VALUES (?,?,?,?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email=?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getLong("id"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleId(Integer.parseInt(resultSet.getString("role_id")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void editProfile(Users user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET full_name = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changePassword(Users user, String newPassword) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET password = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, newPassword);
            statement.setLong(2, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM news WHERE id = ?"
            );
            statement.setLong(1, news.getId());
            System.out.println("News which wanna delete: " + news.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int commentsCount(Long id) {
        int commentsCount = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) as comm_count FROM comments " +
                            "JOIN news ON comments.news_id = news.id " +
                            "WHERE news.id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                commentsCount = resultSet.getInt("comm_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentsCount;
    }

    public static ArrayList<Comments> commentsOfNews(Long newsId) {
        ArrayList<Comments> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.id, c.comment, c.post_date, c.user_id as user_id, c.news_id as news_id, " +
                            "u.full_name as full_name " +
                            "FROM comments c " +
                            "JOIN users u ON c.user_id = u.id " +
                            "JOIN news n ON c.news_id = n.id " +
                            "WHERE news_id = ? " +
                            "ORDER BY c.post_date DESC"
            );
            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comments comment = new Comments();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                Users users = new Users();
                users.setId(resultSet.getLong("user_id"));
                users.setFullName(resultSet.getString("full_name"));
                comment.setUser(users);
                News news = new News();
                news.setId(resultSet.getLong("news_id"));
                comment.setNews(news);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(Comments comments) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments (comment, post_date, user_id, news_id) " +
                            "VALUES (?, NOW(), ?, ?)"
            );
            statement.setString(1, comments.getComment());
            statement.setInt(2, Integer.parseInt(comments.getUser().getId().toString()));
            statement.setInt(3, Integer.parseInt(comments.getNews().getId().toString()));
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteComments(Long newsId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM comments WHERE news_id = ?"
            );
            statement.setLong(1, newsId);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
