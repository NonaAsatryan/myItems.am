<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 25.02.22
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<% List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<a href="/userRegister">Register</a><br>
<a href="/login">Login</a>
<div>
    <table border="1">
        <tr>
            <% for (Category category : categories) { %>

            <td>
                <a href="/category"> <%=category.getName()%></a>
            </td>
            <% } %>
        </tr>
    </table>
</div>
<div>
    <table border="1">
        <tr>
            <th>title</th>
            <th>price</th>
        </tr>
        <% for (Item item : items) {%>
        <tr>
            <td><%=item.getTitle() %>
            </td>
            <td><%=item.getPrice() %>
            </td>
            <td><%
                if (item.getPictureUrl() != null) {%>
                <img src="/image?path=<%=item.getPictureUrl()%>" width="80"/>
                <%}%></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
