<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="custom.css" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title> Preview Post </title>
</head>
<body>
    <%-- navigation bar --%>
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center">
        <span class="navbar-text" style="font-size: 250%; color:white;"> Preview Post </span>
    </nav>
    <br>
    <div>
        <form action="post" method="POST">
            <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
            <input type="hidden" name="postid" value='<%= request.getAttribute("postid")%>'>
            <input type="hidden" name="title" value='<%= request.getAttribute("title") %>'>
            <input type="hidden" name="body" value='<%= request.getAttribute("body") %>'>
            <div class="buttonHolder">
                <button type="submit" name="action" value="open" class="btn btn-secondary">Close Preview</button>
            </div>
        </form>
    </div>
    <br>
    <div style="margin-left: auto; margin-right: auto; width: 80%">
        <h1 id="title"><p> <%= request.getAttribute("renderedTitle") %> </p></h1>
        <div id="body"><p> <%= request.getAttribute("renderedBody") %></p>
    </div>
</body>
</html>
