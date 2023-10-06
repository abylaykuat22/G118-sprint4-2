<%@ page import="java.util.List" %>
<%@ page import="kz.bitlab.model.Item" %><%--
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
<div class="col-8 mx-auto">
    <h1 class="text-center">WELCOME TO BITLAB SHOP</h1>
    <h5 class="text-center">Electronic devices with high quality and service</h5>
    <div class="d-flex">
        <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            for (Item item : items) {
        %>
        <div class="card col-4 m-2">
            <div class="card-header"><%=item.getName()%></div>
            <div class="card-body">
                <h5>$<%=item.getPrice()%></h5>
                <p><%=item.getDescription()%></p>
                <a href="#" class="btn btn-primary w-100">BUY NOW</a>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
