<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 25.02.22
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<% List<Item> allItems = (List<Item>) request.getAttribute("items");
    List<Category> categoryList = (List<Category>) request.getAttribute("categories");
    User user = (User) session.getAttribute("user");%>
Welcome <%=user.getName()%>
<div>
    Add Item <br>
    <form action="/addItem" method="post" enctype="multipart/form-data"/>
    <input type="text" name="title" placeholder="title"/><br>
    <input type="text" name="price" placeholder="price"/><br>
    <input type="hidden" name="userId" value="<%=user.getId()%>"/>
    <input type="file" name="image"/> ><br>

    <select name="category_id">
        <% for (Category category : categoryList) { %>
        <option value="<%=category.getId()%>">
            <%=category.getName()%>
        </option>
        <% } %>
    </select><br>
    <input type="submit" value="Add">
    </form>
</div>
<div>
    <table border="1">
        <tr>
            <th>title</th>
            <th>price</th>
        </tr>
        <% for (Item item : allItems) { %>
        <tr>
            <td><%=item.getTitle() %>
            </td>
            <td><%=item.getPrice() %>
            </td>
            <td><a href="/deleteItem?id=<%=item.getId()%>">Delete</a></td>
            <td><%
                if (item.getPictureUrl() != null) {%>
                <img src="/image?path=<%=item.getPictureUrl()%>"width="80"/>
                <% } %></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
