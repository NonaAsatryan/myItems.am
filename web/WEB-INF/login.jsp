<%--
  Created by IntelliJ IDEA.
  User: nona.asatryan
  Date: 25.02.22
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/logout">Logout</a><br>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="email"><br>
    <input type="password" name="password" placeholder="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
