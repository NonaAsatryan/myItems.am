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
    <title>Register</title>
</head>
<body>
<a href="/home">Back</a>
<form action="/register" method="post" >
    <input type="text" name="name" placeholder="name"/><br>
    <input type="text" name="surname" placeholder="surname"/><br>
    <input type="email" name="email" placeholder="email"/><br>
    <input type="password" name="password" placeholder="password"/><br>
    <input type="text" name="phone" placeholder="phone"/><br>
    <input type="submit" value="register">
</form>
</body>
</html>
