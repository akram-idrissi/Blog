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
                    <img class="profile-image" src="" alt="profile Image"/>
                    <div class="user-data">
                        <p class="profile-username"></p>
                        <p class="profile-email"></p>
                    </div>
                </div>

                <h2>Profile Info</h2>
                <hr>
                <form id="profile-form" method="post" enctype="multipart/form-data" onsubmit="profile(this)">

                    <label>Username*</label><br>
                    <input class="profile-username-in" type="text" name="username" placeholder=" " value="" readonly="readonly"><br>
                    <span class="username-rules">
                        Required 30 characters or fewer. Letters, digits and @/./+/-/_ only.
                    </span><br>

                    <label>Email*</label><br>
                    <input class="profile-email-in" type="email" name="email" placeholder=" " value="" readonly="readonly"><br>

                    <h2>Image</h2>
                    <hr>
                    <div class="image-info">
                        <span>Currently:</span><span id="img-path"></span><br>
                        <span class="change-img-button">Change:
                            <input type="file" name="image" id="input-img">
                        </span>
                    </div>
                        <input type="submit" value="Update" >
                </form>
            </div>
        </div>
    </div>
                        
    <script src="javascript/profile.js"></script>
    
    
<jsp:include page="includes/endHtml.jsp" />