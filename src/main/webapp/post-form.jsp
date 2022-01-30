<jsp:include page="includes/startHtml.jsp" /> 
<jsp:include page="includes/homeHeader.jsp" /> 
<jsp:include page="includes/sideBar.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Body here -->

    <!-- if someone attempts to access this page without having permission(log in) -->
    <%
        session = request.getSession();
        if (session.getAttribute("user") == null){
            response.sendRedirect("login.jsp");
        }
    %>
    
    <div class="container">
        <div class="new-post">
            <div class="padding">
                <form id="new-post-form" action="post" method="post">
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
