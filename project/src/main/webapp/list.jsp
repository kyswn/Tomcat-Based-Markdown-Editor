<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page import="java.util.ArrayList" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post List</title>
</head>
<body>
    <div>
        <form action="post" id="0">
            <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
            <input type="hidden" name="postid" value="0">
            <button type="submit" name="action" value="open">New Post</button>
        </form>
    </div>
     <% ArrayList<Integer> postids = (ArrayList<Integer>) request.getAttribute("postids") %>
    <% ArrayList<String> titles = (ArrayList<String>) request.getAttribute("titles") %>
    <% ArrayList<Timestamp> modifieds = (ArrayList<Timestamp>) request.getAttribute("modifieds") %>
    <% ArrayList<Timestamp> createds = (ArrayList<Timestamp>) request.getAttribute("createds") %>
    <table>
        <tr><th>Title</th><th>Created</th><th>Modified</th><th>&nbsp;</th></tr>
        <c:forEach var="id" items="${postids}" varStatus="status">
            <tr>
                <form action="post" method="POST">
                    <input type="hidden" name="username" value='<%= request.getParameter("username") %>'>
                    <input type="hidden" name="postid" value="${id}">
                    <td> ${titles[status.index]}</td>
                    <td> ${createds[status.index]}</td>
                    <td> ${modifieds[status.index]}</td> 
                    <td>
                        <button type="submit" name="action" value="open">Open</button>
                        <button type="submit" name="action" value="delete">Delete</button>
                    </td>
                </form>
            </tr>
        </c:forEach> 
    </table>
</body>
</html>