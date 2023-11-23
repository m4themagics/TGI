<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h1>Students</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <!-- Add other fields as necessary -->
    </tr
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <!-- Add other fields as necessary -->
        </tr>
    </c:forEach>
</table>
</body>
</html>