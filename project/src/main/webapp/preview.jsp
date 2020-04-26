<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Preview Post </title>
</head>
<body>
    <div><h1>Preview Post</h1></div>
    <div>
        <form action="post" method="POST">
            <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
            <input type="hidden" name="postid" value='<%= request.getAttribute("postid")%>'>
            <input type="hidden" name="title" value='<%= request.getAttribute("title") %>'>
            <input type="hidden" name="body" value='<%= request.getAttribute("body") %>'>
            <button type="submit" name="action" value="open">Close Preview</button>
        </form>
    </div>
    <div>
        <h1 id="title"><p> <%= request.getAttribute("renderedTitle") %> </p></h1>
        <div id="body"><p> <%= request.getAttribute("renderedBody") %></p>
</div>
        </div>
</body>
</html>
