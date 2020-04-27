<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="custom.css" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Invalid Request</title>
</head>
<body>
    <%-- navigation bar --%>
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center">
        <span class="navbar-text" style="font-size: 250%; color:white;"> ERROR: 404 PAGE NOT FOUND </span>
    </nav>
    <br>
    <h2>Request</h2>
    <div style="margin-left: auto; margin-right: auto; width: 80%">
        <b>action: </b><%= request.getAttribute("action") %><br>
        <b>username: </b><%= request.getAttribute("username") %><br>
        <b>postid: </b><%= request.getAttribute("postid") %><br>
        <b>title: </b><%= request.getAttribute("title") %><br>
        <b>body: </b><%= request.getAttribute("body") %><br>
    </div>
    <br>
    <h2>Reason of Error</h2>
    <div style="margin-left: auto; margin-right: auto; width: 80%">
        <b><%= request.getAttribute("errorMessage") %></b>
    </div>
</body>
</html>
