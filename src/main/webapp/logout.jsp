<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
<jsp:include page="includes/startHtml.jsp" /> 
    <div class="container">
        <div class="logout">
            <h2>You have been logged out</h2>
            <hr>
            <a href="login.jsp">Log In Again</a>
        </div>
    </div>
<jsp:include page="includes/endHtml.jsp" />
