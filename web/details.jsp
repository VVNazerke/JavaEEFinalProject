<%@ page import="model.NewsCategory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.News" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Comments" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <%
        News news = (News) request.getAttribute("novost");
    %>
    <div class="mt-3">
        <%
            Date date = new Date(news.getPostDate().getTime());
        %>
        <label class="text-secondary"><%=date%>
        </label>
    </div>
    <div class="mt-2">
        <h3 class="text-center"><%=news.getTitle()%>
        </h3>
    </div>
    <div>
        <label class="text-secondary">#<%=news.getNewsCategory().getName()%>
        </label>
    </div>
    <div class="mt-1">
        <p>&emsp; <%=news.getContent()%>
        </p>
    </div>

    <%
        if (currentUser.getRoleId() == 1) {
    %>
    <div class="d-flex justify-content-start">
        <div>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal">
                Edit
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form method="post" action="/details">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Edit News</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <input type="hidden" value="<%=news.getId()%>" name="news_id">
                                <div class="mb-3">
                                    <h5 for="exampleCategory" class="form-label">Category:</h5>
                                    <select class="form-select" id="exampleCategory" name="re_category_id">
                                        <%
                                            ArrayList<NewsCategory> newsCategories = (ArrayList<NewsCategory>)
                                                    request.getAttribute("kategorii");
                                            if (newsCategories != null && news != null) {
                                                for (NewsCategory nc : newsCategories) {
                                        %>
                                        <option
                                                <%=nc.getName().equals(news.getNewsCategory().getName()) ? "selected" : ""%>
                                                value="<%=nc.getId()%>">
                                            <%=nc.getName()%>
                                        </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <h5 for="exampleTitle" class="form-label">Title:</h5>
                                    <textarea class="form-control" id="exampleTitle" rows="3"
                                              name="re_title"><%=news.getTitle()%></textarea>
                                </div>
                                <div class="mb-3">
                                    <h5 for="exampleContent" class="form-label">Content:</h5>
                                    <textarea class="form-control" id="exampleContent" rows="7"
                                              name="re_content"><%=news.getContent()%></textarea>
                                </div>


                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <button type="button" class="btn btn-outline-danger ms-3" data-bs-toggle="modal"
                    data-bs-target="#exampleDelete">
                Delete
            </button>

            <div class="modal fade" id="exampleDelete" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <form action="/delete-news" method="post">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleDeleteLabel">Delete News</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" value="<%=news.getId()%>" name="news_id">
                                <label>Are you sure?</label>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                <button type="submit" class="btn btn-danger">Yes</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>

    <div class="mt-5">
        <h5>
            <%
                int commCount = (int) request.getAttribute("commCount");
            %>
            <%=commCount%> comments
        </h5>
    </div>
    <div class="mt-3">
        <form method="post" action="/comment">
            <input type="hidden" name="user_email" value="<%=currentUser.getEmail()%>">
            <input type="hidden" name="news_id_c" value="<%=news.getId()%>">
            <div class="form-floating">
                <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2"
                          style="height: 60px" name="comment_text"></textarea>
                <label for="floatingTextarea2">Comments</label>
            </div>
            <button class="btn btn-outline-success btn-sm mt-2">Send</button>
        </form>
    </div>

    <div>
        <%
            ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("comments");
            for (Comments comment : comments) {
        %>
        <div class="card cd-2 card-body mt-3">
            <div class="d-flex justify-content-between">
                <h6><%=comment.getUser().getFullName()%>
                </h6>
                <label class="text-secondary"><%=comment.getPostDate()%>
                </label>
            </div>
            <p class="mb-0"><%=comment.getComment()%>
            </p>
        </div>

        <%
            }
        %>
    </div>
</div>

</body>
</html>
