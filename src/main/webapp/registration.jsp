<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h3>Registration here : </h3>
<h3>Welcome user : <b><%= request.getParameter("email")%>
<form action="/web_project_war_exploded/registration" method="post">
    <label>First name : </label>
    <input type="text" name="firstName"><br>
    <label>Last name : </label>
    <input type="text" name="lastName"><br>
    <label>Email : </label>
    <input type="email" name="email"><br>
    <label>Password : </label>
    <input type="password" name="password"><br>
    <input type="submit" value="OK">
</form>

<h5> <b><%= request.getParameter("message")%>
</b></h5>
</body>
</html>
