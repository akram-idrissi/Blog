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

    <div class="container">
        <ul class="posts">
            <li class="post">
                <div class="padding">
                    <div class="post-content">
                        <span class="hidden-date" style="display: none">${post.getPostedDate()}</span>
                        <img src="images/${post.getUser().getImage()}" alt="profile image">
                        <div class="post-desc">
                            <span class="username">${post.getUser().getUsername()}</span>

                            <span class="post-date">${post.getPostedDate()}</span>
                            <hr>
                            <a>
                                <h2>${post.getTitle()}</h2>
                            </a>
                            <p class="content">${post.getContent()}</p>
                            <button onclick="readMore(this)" class="read-more-btn">Read More</button>

                            <div class="insights">
                                <a onclick="likeInsight(this)" href="javascript:void(0);" class="like">
                                    <i class="bi bi-hand-thumbs-up"></i><span class="likeInsight">${post.getLikeCount()}</span>
                                </a>

                                <a onclick="dislikeInsight(this)" href="javascript:void(0);" class="dislike">
                                    <i class="bi bi-hand-thumbs-down"></i><span class="dislikeInsight">${post.getDislikeCount()}</span>
                                </a>

                                <a href="comments" class="comment">
                                    <i class="bi bi-chat"></i><span class="commentInsight">${post.getCommentCount()}</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>

    <c:choose>
        <c:when test="${!empty comments}">
            <c:forEach var="comment" items="${comments}">

                <div class="container">
                     <ul class="comments">
                         <h2>Comments</h2>
                         <hr>
                         <li class="comment-page">
                             <div class="padding">
                                 <div class="comment-body">
                                     <img src="images/${comment.getUser().getImage()}" class="avatar" alt="">
                                     <div class="user-info">
                                         <fmt:parseDate value="${comment.getPost().getPostedDate()}" type="date" pattern="yyyy-MM-dd HH:mm:ss" var="fdate" />
                                         <fmt:formatDate value="${fdate}" type="date" pattern="MMM d, yyyy" var="string"/>

                                         <p class="meta">${string}&#160&#160<a href="#">${comment.getUser().getUsername()}</a> says : <i class="pull-right"><a href="#">
                                                     <span class="update-comment"><i class="bi bi-arrow-repeat"></i> </span>
                                                     <span class="delete-comment" ><i class="bi bi-trash"></i></span></a></i>
                                         </p>

                                         <hr class="break-line">
                                         <p class="content" class="comment-text">
                                             ${comment.getContent()}
                                         </p>
                                         <button onclick="readMore(this)" class="read-more-btn">Read More</button>
                                     </div>

                                     <div class="insights">
                                         <a onclick="likeInsight(this)" href="javascript:void(0);" class="like">
                                             <i class="bi bi-hand-thumbs-up"></i><span class="likeInsight">${comment.getLikeCount()}</span>
                                         </a>

                                         <a href="comments?post-date=${post.getPostedDate()}&title=${post.getTitle()}" class="dislike">
                                             <i class="bi bi-hand-thumbs-down"></i> <span class="dislikeInsight">${comment.getDislikeCount()}</span>
                                         </a>
                                     </div>

                                 </div>
                             </div>
                         </li>
                     </ul>
                 </div>
            </c:forEach>
        </c:when>  
        <c:otherwise>
            <div class="container">
                <h2 >Comments</h2>
                <hr class="line">
                <h2 class="no-comments">No comments...</h2>
            </div>

        </c:otherwise>
    </c:choose>
                                
    <div class="container">
        <div class="add-comment">
            <textarea name="content" cols="72" rows="7" placeholder="Your comment..." required></textarea>
            <button type="submit" class="add-comment-btn">Add</button>
        </div>
    </div>

<script src="javascript/readMore.js"></script>
<script src="javascript/comments.js"></script>

<jsp:include page="includes/endHtml.jsp" />
