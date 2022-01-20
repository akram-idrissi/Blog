<jsp:include page="includes/startHtml.jsp" /> 
<jsp:include page="includes/homeHeader.jsp" /> 
<jsp:include page="includes/sideBar.jsp" /> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

    <!-- if someone attempts to access this page without having permission(log in) -->
    <%
        session = request.getSession();
        if (session.getAttribute("user") == null){
            response.sendRedirect("login.jsp");
        }
    %>
    
    <div class="container">
        <div class="update-post">
            <div class="padding">
                <form action="post" method="post">
                    <h2>Update Post</h2>
                    <hr>
                    <br> 
                    <input type="hidden" name="action" value="update">
                    <label>Title*</label><br>
                    <input type="text" name="title" value="${post.getTitle()}"><br>
                    <label>Content*</label><br>
                    <textarea name="content" cols="68" rows="10">${post.getContent()}</textarea>
                    <input type="submit" value="Update Post">
                </form>
            </div>
        </div>
    </div>
    
<jsp:include page="includes/endHtml.jsp" />
