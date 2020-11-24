<%@ page import="model.entites.Hit" %><%--
  Created by IntelliJ IDEA.
  User: seeke
  Date: 23.11.2020
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getAttribute("hit") == null) {
        request.setAttribute("error", new Exception("Empty hit value!"));
        request.getRequestDispatcher("/error.jsp").forward(request,response);
    }
%>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <table class="contentTable" id="res">
        <tbody>
        <tr>
            <td>X</td>
            <td>Y</td>
            <td>R</td>
            <td>Result</td>
        </tr>
        <tr>
            <td><%=((Hit)request.getAttribute("hit")).getXCoordinate()%></td>
            <td><%=((Hit)request.getAttribute("hit")).getYCoordinate()%></td>
            <td><%=((Hit)request.getAttribute("hit")).getRValue()%></td>
            <td><%=((Hit)request.getAttribute("hit")).isInArea()%></td>
        </tr>
        </tbody>
    </table>
    <form action=index.jsp>
        <input type="submit" value="Back">
    </form>
</body>
</html>
