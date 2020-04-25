<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invalid Request</title>
</head>
<body>
    <h1>Invalid Request</h1>
    
    <h2>Request</h2>
    <b>action: </b><%= request.getAttribute("action") %><br>
    <b>username: </b><%= request.getAttribute("username") %><br>
    <b>postid: </b><%= request.getAttribute("postid") %><br>
    <b>title: </b><%= request.getAttribute("title") %><br>
    <b>body: </b><%= request.getAttribute("body") %><br>

    <h2>Reason of Error</h2>
    Invalid action parameter
</body>
</html>
