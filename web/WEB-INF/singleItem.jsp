<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page import="model.ItemImage" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 25.02.22
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    Item item = (Item) request.getAttribute("item");
    List<ItemImage> itemImages = (List<ItemImage>) request.getAttribute("itemImages");
%>
<div style="margin: 0 auto; width: 1000px; height: 1000px; border: 1px solid black">

    <% if (user == null) { %>
    <div style="float:right;"><a href="/login">Մուտք</a> | <a href="/register">Գրանցում</a></div>
    <% } else { %>

    <div> Welcome <%=user.getName()%> | <a href="/myItems">Իմ Հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a></div>
    <%}%>

    <div>
        <ul style="overflow:hidden">
            <li style="display: inline; margin-left:40px;"><a href="/home">Գլխավոր</a></li>
            <% for (Category category : categories) { %>
            <li style="display: inline; margin-left:40px;"><a
                    href="/home?cat_id=<%=category.getId()%>"><%=category.getName()%>
            </a>
            </li>
            <% } %>
        </ul>
    </div>

    <div>
        <div style="width: 500px; float: left">
            <div>
                <% for (ItemImage itemImage : itemImages) { %>
                    <% if (itemImage.getImageUrl() != null && !itemImage.getImageUrl().equals("")) {%>
                <img src="/getImage?itemImage_url=<%=itemImage.getImageUrl()%>" width="500"/>
                <%} else {%>
                <img src="/img/img.png" width="500"/>
                <%}
                    }%>
            </div>
            <div>
                <span><%=item.getTitle()%> | <%=item.getPrice()%> </span>
            </div>
            <span><%=item.getDescription()%></span>
            <div>
                User
                Info: <%=item.getUser().getName()%> <%=item.getUser().getSurname()%> <%=item.getUser().getPhone()%>
            </div>
        </div>

    </div>
</div>
</body>
</html>
