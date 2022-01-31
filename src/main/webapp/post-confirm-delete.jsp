<jsp:include page="includes/startHtml.jsp" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <div class="container">
        <div class="delete-post">
            <div class="padding">
                <form action="post" method="post">
                    <h2>Delete Post</h2>
                    <hr>
                    <br>
                    <h2>Are you sure you want to delete the post "${title}"?</h2>
                    <div class="confirmation-buttons">
                        <button type="submit" name="action" value="delete" class="yes-delete-button">Yes, Delete</button>
                        <a href="home" id="cancel">Cancel</a>
                    </div>
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