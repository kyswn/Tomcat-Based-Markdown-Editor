<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="custom.css" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Edit Post</title>
</head>
<body>
    <%-- navigation bar --%>
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center">
        <span class="navbar-text" style="font-size: 250%; color:white;"> Edit Post </span>
    </nav>
    <br>

    <%-- main body part --%>
    <div class="wrapper">
    <form action="post" method="POST">
        <div class="buttonHolder">
            <button type="submit" name="action" value="save" class="btn btn-success">Save</button>
            <button type="submit" name="action" value="list" class="btn btn-secondary">Close</button>
            <button type="submit" name="action" value="preview" class="btn btn-secondary">Preview</button>
            <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
        </div>
        <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
        <input type="hidden" name="postid" value='<%= request.getAttribute("postid") %>'>
        <br>

        <div style="margin-left: auto; margin-right: auto; width: 80%">
        <div>
            <label for="title"> Title </label>
            <input type="text" class="form-control" placeholder="Enter Title" name="title" value='<%= request.getAttribute("title")%>'>
        </div>
        <div>
            <label for="body">Body</label>
            <textarea class="form-control" rows="5" name="body" placeholder="Enter Text"><%= request.getAttribute("body")%></textarea>
        </div>
        </div>
    </form>
    </div>
</body>
</html>