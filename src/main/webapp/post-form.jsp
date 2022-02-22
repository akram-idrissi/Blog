<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/startHtml.jsp" /> 
    <div class="container">
        <div class="new-post">
            <div class="padding">
                <form id="new-post-form" action="/post" method="post">
                    <h2>New Post</h2>
                    <hr>
                    <br> 
                    <input type="hidden" name="action" value="add">
                    <label>Title*</label><br>
                    <input type="text" name="title" required><br>
                    <label>Content*</label><br>
                    <textarea name="content" cols="68" rows="10" required></textarea>
                    <input type="submit" value="Post">
                </form>
            </div>
        </div>
    </div>
<jsp:include page="includes/endHtml.jsp" />

<!-- if someone attempts to access this page without having permission(log in) -->
<%
    session = request.getSession();
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>