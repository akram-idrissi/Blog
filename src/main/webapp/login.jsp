<jsp:include page="includes/startHtml.jsp" /> 
<jsp:include page="includes/header.jsp" /> 
<jsp:include page="includes/sideBar.jsp" /> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="container">
        <div class="login">
            <div class="padding">
                <h2>Log In</h2>
                <hr>
                <form action="login" method="post">
                    <label>Username*</label><br>
                    <input type="text" name="username" placeholder=""><br>

                    <label>Password*</label><br>
                    <input type="password" name="password" placeholder=""><br>

                    <input type="submit" value="Login">
                    <a href="request-pass-reset.jsp">Forgot Password?</a>
                </form>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="sigin">
            <hr>
            <span>Need An Account?</span><a href="register.jsp">Sign Up Now</a>
        </div>
    </div>
<jsp:include page="includes/endHtml.jsp" /> 
