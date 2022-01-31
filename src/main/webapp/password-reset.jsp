<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/startHtml.jsp" />
<jsp:include page="message.jsp" /> 
    <div class="container">
        <div class="reset-pass-request">
            <div class="padding">
                <h2>Reset Password</h2>
                <hr>
                <form action="email" method="post">
                    <label>Email*</label><br>
                    <input type="email" name="email" placeholder=" "><br>
                    <input type="submit" value="Request Password Reset">
                </form>
            </div>
        </div>
    </div>
<jsp:include page="includes/endHtml.jsp" />
