<%
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="includes/startHtml.jsp" /> 
<jsp:include page="message.jsp" /> 
    <div class="container">
        <div class="profile">
            <div class="padding">
                <div class="profile-user-info">
                    <img class="profile-image" src="images/${userObj.getImage()}" alt="profile Image"/>
                    <div class="user-data">
                        <p class="profile-username">${userObj.getUsername()}</p>
                        <p class="profile-email">${userObj.getEmail()}</p>
                    </div>
                </div>

                <h2>Profile Info</h2>
                <hr>
                <form id="profile-form" action="edit-profile" method="post" enctype="multipart/form-data">

                    <label>Username*</label><br>
                    <input class="profile-username-in" type="text" name="username" placeholder=" " value="${userObj.getUsername()}" readonly="readonly"><br>
                    <span class="username-rules">
                        Required 30 characters or fewer. Letters, digits and @/./+/-/_ only.
                    </span><br>

                    <label>Email*</label><br>
                    <input class="profile-email-in" type="email" name="email" placeholder=" " value="${userObj.getEmail()}" readonly="readonly"><br>

                    <h2>Image</h2>
                    <hr>
                    <div class="image-info">
                        <span>Currently:</span><span id="img-path">${userObj.getImage()}</span><br>
                        <span class="change-img-button">Change:
                            <input type="file" name="image" id="input-img">
                        </span>
                    </div>
                    <input type="submit" value="Update" >
                </form>
            </div>
        </div>
    </div>
<jsp:include page="includes/endHtml.jsp" />
