<%@ page import="java.util.ArrayList" %>
<%@ page import="model.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <div>
        <%
            ArrayList<News> news = (ArrayList<News>) request.getAttribute("novosti");
            if (news != null) {
                for (News n : news) {

        %>
        <div class="card mt-4">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <h5><%=n.getNewsCategory().getName()%>
                    </h5>
                    <h6><%=n.getPostDate()%>
                    </h6>
                </div>
            </div>

            <div class="card-body">
                <h5 class="card-title"><%=n.getTitle()%>
                </h5>
                <p class="card-text"><%=n.getContent()%>
                </p>
                <a href="details?news_id=<%=n.getId()%>" class="btn btn-outline-warning">Details</a>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>


</body>
</html>
