<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Doctors Page</title>
</head>
<body>
<h1>Doctor List</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    <c:forEach var="mydoctor" items="${doctorList}">
        <tr>
            <td>${doctor.getName()}</td>
            <td>${doctor.getSurname()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>