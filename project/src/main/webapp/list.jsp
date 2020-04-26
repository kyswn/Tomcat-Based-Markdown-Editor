<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="custom.css" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Post List</title>
</head>
<body>
    <%-- navigation bar --%>
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center">
        <span class="navbar-text" style="font-size: 250%; color:white;"> Your List </span>
    </nav>
    <br>
    <div style="margin-left: auto; margin-right: auto; width: 80%">
    <div>
        <form action="post" id="0">
            <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
            <input type="hidden" name="postid" value="0">
            <button type="submit" name="action" value="open" class="btn btn-secondary">New Post</button>
        </form>
    </div>
    <% ArrayList<Integer> postids = (ArrayList<Integer>) request.getAttribute("postids"); %>
    <% ArrayList<String> titles = (ArrayList<String>) request.getAttribute("titles") ;%>
    <% ArrayList<Timestamp> modifieds = (ArrayList<Timestamp>) request.getAttribute("modifieds"); %>
    <% ArrayList<Timestamp> createds = (ArrayList<Timestamp>) request.getAttribute("createds"); %>
    <table class="table">
        <thread class="thread-light">
            <tr>
                <th>Title</th>
                <th>Created</th>
                <th>Modified</th>
                <th>Operation</th>
            </tr>
        </thread>
        <tbody>
            <c:forEach var="id" items="${postids}" varStatus="status">
                <tr>
                    <form action="post" method="POST">
                        <input type="hidden" name="username" value='<%= request.getAttribute("username") %>'>
                        <input type="hidden" name="postid" value="${id}">
                        <td> ${titles[status.index]}</td>
                        <td> ${createds[status.index]}</td>
                        <td> ${modifieds[status.index]}</td> 
                        <td>
                            <button type="submit" name="action" value="open" class="btn btn-success">Open</button>
                            <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                        </td>
                    </form>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
    </div>
</body>
</html>