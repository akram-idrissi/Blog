<jsp:include page="includes/startHtml.jsp"/>
<jsp:include page="includes/header.jsp" />
<jsp:include page="includes/sideBar.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="container">
        <div class="register">
            <div class="padding">
                <h2>Join Today</h2>
                <hr>
                <form action="register" method="post">
                    <label>Username*</label><br>
                    <input type="text" name="username" placeholder=" "><br>
                    <span class="confirm-pass-message">
                        Required 30 characters or fewer. Letters, digits and @/./+/-/_ only.
                    </span><br>

                    <label>Email*</label><br>
                    <input type="email" name="email" placeholder=" "><br>

                    <label>Password*</label><br>
                    <input type="password" name="password" placeholder=" "><br>

                    <ul>
                        <li>Your password must contain at least 8 characters.</li>
                        <li>Your password can't be a commonly used password.</li>
                        <li>Your password can't be similar to your other personal information.</li>
                        <li>Your password must at least have lowercase and uppercase letters, numbers, and symbols.</p></li>
                    </ul>

                    <label>Confirm Password*</label><br>
                    <input type="password" name="confirmPassword" placeholder=" ">
                    <label class="confirm-pass-message">Enter the same password as before, for verification.</label><br>
                    <input type="submit" value="Sign Up">

                </form>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="signup">
            <hr>
            <span>Already Have An Account?</span><a href="login.jsp">Sign In</a>
        </div>
    </div>


<jsp:include page="includes/endHtml.jsp" />
