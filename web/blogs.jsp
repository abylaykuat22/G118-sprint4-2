<%@ page import="java.util.List" %>
<%@ page import="kz.bitlab.model.Item" %>
<%@ page import="kz.bitlab.model.City" %>
<%@ page import="kz.bitlab.model.Blog" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 06.10.2023
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container col-8 mx-auto">
    <form action="/blogs" method="post">
        <input class="form-control mt-1" type="text" name="blog_title" placeholder="Insert title...">
        <input class="form-control mt-1" type="text" name="blog_content" placeholder="Insert content...">
        <button>ADD BLOG</button>
    </form>

    <table class="table table-striped">
        <thead>
            <th>ID</th>
            <th>TITLE</th>
            <th>POST DATE</th>
            <th>DETAILS</th>
        </thead>
        <tbody>
            <%
                var blogs = (List<Blog>) request.getAttribute("novosti");
                for (Blog blog : blogs) {
            %>
                <tr>
                    <td><%=blog.getId()%></td>
                    <td><%=blog.getTitle()%></td>
                    <td><%=blog.getPostDate()%></td>
                    <td><a href="/blog-details?id=<%=blog.getId()%>" class="btn btn-dark">DETAILS</a></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
