    <div class="container">
        <div class="message">
            <p class="${cssClass}" style="display: ${style}">${message}</p>
        </div>
    </div>
<%
session.removeAttribute("style");
session.removeAttribute("message");
session.removeAttribute("cssClass");
%>