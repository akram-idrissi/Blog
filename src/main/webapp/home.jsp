<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    
    <c:if test="${posts != null}">
        <c:forEach var="post" items="${posts}">

            <div class="container">
                <ul class="posts">
                    <li class="post">
                        <div class="padding">
                            <div class="post-content">
                                <span class="hidden-date" style="display: none">${post.getPostedDate()}</span>
                                <img src="images/${post.getUser().getImage()}" alt="profile image">
                                <div class="post-desc">
                                    <span class="username">${post.getUser().getUsername()}</span>

                                    <fmt:parseDate value="${post.getPostedDate()}" type="date" pattern="yyyy-MM-dd HH:mm:ss" var="fdate" />
                                    <fmt:formatDate value="${fdate}" type="date" pattern="MMM dd, yyyy" var="string"/>
                                    <span class="post-date">${string}</span>
                                    <hr>
                                    <a href="post-detail?post-date=${post.getPostedDate()}&title=${post.getTitle()}">
                                        <h2 class="post-title">${post.getTitle()}</h2>
                                    </a>
                                    <p class="content">${post.getContent()}</p>
                                    <span onclick="readMore(this)" class="read-more-btn">Read More</span>

                                    <div class="insights">
                                        <a onclick="likeInsight(this)" href="javascript:void(0);" class="like">
                                            <i class="bi bi-hand-thumbs-up"></i><span class="likeInsight">${post.getLikeCount()}</span>
                                        </a>

                                        <a onclick="dislikeInsight(this)" href="javascript:void(0);" class="dislike">
                                            <i class="bi bi-hand-thumbs-down"></i><span class="dislikeInsight">${post.getDislikeCount()}</span>
                                        </a>

                                        <a href="comments?post-date=${post.getPostedDate()}&title=${post.getTitle()}&see-more=false" class="comment">
                                            <i class="bi bi-chat"></i><span class="commentInsight">${post.getCommentCount()}</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

        </c:forEach>
    </c:if>

    
            <div class="container">
                <div class="home-pagination">
                    <c:forEach var="p" items="${pages}">
                        <c:choose>
                            <c:when test="${page == null && p == 1}">
                                <a class="current-page" href="home-posts?page=${p}">${p}</a>
                            </c:when>
                            <c:when test="${page == p}">
                                <a class="current-page" href="home-posts?page=${p}">${p}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="home-posts?page=${p}">${p}</a>
                            </c:otherwise>
                        </c:choose>
                        
                    </c:forEach>
                </div>
            </div>
        
    
    
<script src="javascript/insight.js"></script>
<script src="javascript/readMore.js"></script>

<jsp:include page="includes/endHtml.jsp" />
