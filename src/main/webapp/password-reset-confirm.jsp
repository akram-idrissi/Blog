<jsp:include page="includes/startHtml.jsp"/>
<jsp:include page="includes/header.jsp" />
<jsp:include page="includes/sideBar.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="container">
        <div class="reset-password">
            <div class="padding">
                <h2>Change Password</h2>
                <hr>
                <form action="reset-password" method="post">
                    <label>Password*</label><br>
                    <input type="password" name="new-pass" placeholder=""><br>
                    <ul>
                        <li>Your password must contain at least 8 characters</li>
                        <li>Your password can't be similar to your other personal information</li>
                        <li>Your password must at least have lowercase, uppercase letters, numbers, and symbols</li>
                    </ul>
                    <label>Confirm Password*</label><br>
                    <input type="password" name="new-confirm" placeholder="">
                    <label class="confirm-pass-message">Enter the same password as before, for verification.</label><br>
                    <input type="submit" value="Change">
                </form>
            </div>
        </div>
    </div>
    
<jsp:include page="includes/endHtml.jsp" />
