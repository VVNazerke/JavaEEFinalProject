<%@ page import="java.util.ArrayList" %>
<%@ page import="model.NewsCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <div class="row">
        <div class="col-6">
            <div class="mt-3">
                <form method="post" action="add-news">
                    <div class="mb-3">
                        <h5 for="exampleCategory" class="form-label">Category:</h5>
                        <select class="form-select" id="exampleCategory" name="category_name">
                            <%
                                ArrayList<NewsCategory> newsCategories = (ArrayList<NewsCategory>)
                                        request.getAttribute("kategorii");
                                if (newsCategories != null) {
                                    for (NewsCategory nc : newsCategories) {
                            %>
                            <option value="<%=nc.getId()%>"><%=nc.getName()%>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <h5 for="exampleTitle" class="form-label">Title:</h5>
                        <textarea class="form-control" id="exampleTitle" rows="3" name="title"></textarea>
                    </div>
                    <div class="mb-3">
                        <h5 for="exampleContent" class="form-label">Content:</h5>
                        <textarea class="form-control" id="exampleContent" rows="7" name="content"></textarea>
                    </div>
                    <button class="btn btn-outline-warning btn-sm">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
