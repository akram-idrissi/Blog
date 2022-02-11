<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" href="styles/main.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Blog</title>
</head>

<body> 
    <input type="hidden" id="hidden-id" value="${user}">
    <div class="header">
        <div class="chat-container">
            <div class="header-links">
                <a id="to-home" href="home">
                    <i class="bi bi-house-door"></i>
                </a>
            </div>
        </div>
    </div>
    
    <div class="chat-container">
        <h2>Available users</h2>
        <hr>
    </div>
    <c:forEach var="u" items="${users}">
    <div class="chat-container">
        <c:if test="${u.getId() != user}">
            <a href="private-chat?id=${u.getId()}">${u.getUsername()}</a>
        </c:if>
    </div>
    </c:forEach>
</body>
</html>