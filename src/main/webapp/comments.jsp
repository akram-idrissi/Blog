<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="includes/startHtml.jsp"/> 

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
        <ul class="comments">
            <h2>Comments</h2>
            <hr>
            <li class="comment-page">
                <div class="padding">
                    <div class="comment-body">
                        <img src="https://bootdey.com/img/Content/user_1.jpg" class="avatar" alt="">
                        <div class="user-info">
                            <p class="meta">Dec 18, 2014 <a href="#" class="user-comment">JohnDoe</a> says :</p>
                            <hr class="break-line">
                            <p class="content" class="comment-text">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.


                            </p>
                            <button onclick="readMore(this)" class="read-more-btn">Read More</button>
                        </div>

                        <div class="insights">
                            <a onclick="likeInsight(this)" href="javascript:void(0);" class="like">
                                <i class="bi bi-hand-thumbs-up"></i><span class="likeInsight"></span>
                            </a>

                            <a onclick="dislikeInsight(this)" href="javascript:void(0);" class="dislike">
                                <i class="bi bi-hand-thumbs-down"></i> <span class="dislikeInsight"></span>
                            </a>
                        </div>

                    </div>
                </div>
            </li>
        </ul>
    </div>

<script src="javascript/readMore.js"></script>

<jsp:include page="includes/endHtml.jsp" />
