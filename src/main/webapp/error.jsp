<%--
  Created by IntelliJ IDEA.
  User: seeke
  Date: 23.11.2020
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%=request.getAttribute("error") == null ? "Empty body of error!": request.getAttribute("error")%>
    <form action=index.jsp>
        <input type="submit" value="Back">
    </form>
</body>
</html>
