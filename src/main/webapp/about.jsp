<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/startHtml.jsp" /> 

<c:choose>
    <c:when test="${user == null}">
        <jsp:include page="includes/header.jsp" />
    </c:when>    
    <c:otherwise>
        <jsp:include page="includes/homeHeader.jsp" />
    </c:otherwise>
</c:choose>
 
<jsp:include page="includes/sideBar.jsp" />

<div class="container">
    <h2 class="about-title">About Page.</h2>
</div>

<jsp:include page="includes/endHtml.jsp" />
