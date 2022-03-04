<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ItemImage" %><%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 02.03.22
  Time: 00:36
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
    List<Item> items = (List<Item>) request.getAttribute("items");
    List<ItemImage> itemImages = (List<ItemImage>) request.getAttribute("itemImages");
%>
<div style="margin: 0 auto; width: 1000px; height: 1000px; border: 1px solid black">


    <div> Welcome <%=user.getName()%> | <a href="/myItems">Իմ Հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a>
        | <a href="/logout">Logout</a></div>


    <div>
        <% if (items != null) {%>
        <% for (Item item : items) { %>
        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="width: 105px; float: left">
                <div>
                    <% for (ItemImage itemImage : itemImages) { %>
                        <%if (itemImage.getImageUrl() != null && !itemImage.getImageUrl().equals("")) {%>
                    <img src="/getImage?itemImage_url=<%=itemImage.getImageUrl()%>" width="100"/>
                    <%} else {%>
                    <img src="/img/img.png" width="100"/>
                    <%}
                        }%>
                </div>
                <div>
                    <span><%=item.getTitle()%> | <%=item.getPrice()%> </span>
                </div>
            </div>
        </a>
        <% }
        }%>
    </div>
</div>
</body>
</html>
