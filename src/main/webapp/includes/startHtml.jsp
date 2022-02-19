<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" href="styles/main.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <script src="https://kit.fontawesome.com/dfdc98764d.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <title>Blog</title>
</head>

<body>

    <div class="header">
        <div class="container">
            <div class="header-links">
                <a href="home" id="logo">JEE Blog</a>
                <c:choose>
                    <c:when test="${user == null}">
                <div id="links">
                    <div class="left-links">
                        <a href="home">Home</a>
                        <a href="about.jsp">About</a>
                    </div>

                    <div class="right-links">
                        <a href="login.jsp">Login</a>
                        <a href="register.jsp">Register</a>
                    </div>
                    <div class="sidebar-links no-user">
                        <!--<a  style="color: #a4a5a8" href="login.jsp">Private chat</a>-->
                        <a  style="color: #a4a5a8" href="login.jsp">Join chat room</a>
                    </div>
                </div>
                </c:when>    
                <c:otherwise>
                <div id="links">
                    <div class="left-links">
                        <a href="home">Home</a>
                        <a href="about.jsp">About</a>
                    </div>

                    <div class="right-links">
                        <a href="post-form.jsp">New Post</a>
                        <a href="profile">Profile</a>
                        <a href="logout">Logout</a>
                    </div>
                    
                    <div class="sidebar-links user">
                        <!--<a  style="color: #a4a5a8" href="chat.jsp">Private chat</a>-->
                        <a  style="color: #a4a5a8" href="chat-room.jsp">Join chat room</a>
                    </div>
                </div>
                </c:otherwise>
            </c:choose>
                <div id="menu">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>

            </div>
        </div>
    </div>

    <div class="container">
        <div class="side-bar">
            <div class="padding">
                <h2>Our Sidebar</h2>
                <p>You can put any information you'd like here.</p>
                <c:choose>
                    <c:when test="${user == null}">
                    <ul>
                        <!--<li> <a  style="color: #a4a5a8" href="login.jsp">Private chat</a></li>-->
                        <li> <a  style="color: #a4a5a8" href="login.jsp">Join chat room</a></li>
                    </ul>
                    </c:when>
                    <c:otherwise>
                        <ul>
                            <!--<li> <a  style="color: #a4a5a8" href="chat.jsp">Private chat</a></li>-->
                            <li> <a  style="color: #a4a5a8" href="chat-room.jsp">Join chat room</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    