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
        <div class="profile">
            <div class="padding">

                <div class="profile-user-info">
                    <img src="images/${user.getImage()}" alt=""/>
                    <div class="user-data">
                        <p>${user.getUsername()}</p>
                        <p>${user.getEmail()}</p>
                    </div>
                </div>

                <h2>Profile Info</h2>
                <hr>
                <form action="edit-profile" method="post" enctype="multipart/form-data">

                    <label>Username*</label><br>
                    <input type="text" name="username" placeholder=" " value="${user.getUsername()}"><br>
                    <span class="username-rules">
                        Required 30 characters or fewer. Letters, digits and @/./+/-/_ only.
                    </span><br>

                    <label>Email*</label><br>
                    <input type="email" name="email" placeholder=" " value="${user.getEmail()}" readonly="readonly"><br>

                    <h2>Image</h2>
                    <hr>
                    <div class="image-info">
                        <span>Currently:</span><span id="img-path">/${user.getImage()}</span><br>
                        <span class="change-img-button">Change:
                            <input type="file" name="image">
                        </span>
                    </div>
                    <input type="submit" value="Update">
                </form>
            </div>
        </div>
    </div>
    
    
<jsp:include page="includes/endHtml.jsp" />