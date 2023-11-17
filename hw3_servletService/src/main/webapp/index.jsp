<%@ page import="com.myapp.model.Student" %>
<%@ page import="com.myapp.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students and Their Courses</title>
</head>
<body>
<h1>Students and Their Courses</h1>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    for (Student student : students) {
%>
<p><%= student.getName() %>:</p>
<ul>
    <% for (Course course : student.getCourses()) { %>
    <li><%= course.getName() %>
    </li>
    <% } %>
</ul>
<% } %>
</body>
</html>
