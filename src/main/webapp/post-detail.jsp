<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="includes/startHtml.jsp" /> 
    <div class="container">
        <ul class="posts">
            <li class="post">
                <div class="padding">
                    <div class="post-content">
                        <span class="post-hidden-id" style="display: none">${post.getId()}</span>
                        <img src="images/${post.getUser().getImage()}" alt="profile image">
                        <div class="post-desc">
                            <span class="username">${post.getUser().getUsername()}</span>
                            <fmt:parseDate value="${post.getPostedDate()}" type="date" pattern="yyyy-MM-dd HH:mm:ss" var="fdate" />
                            <fmt:formatDate value="${fdate}" type="date" pattern="MMM dd, yyyy" var="string"/>
                            <span class="post-date">${string}</span>
                            <c:if test="${user == post.getUser().getId()}">
                                <br>
                                <a href="post-update.jsp"><button type="submit" id="update-post-btn" name="action" value="update">Update</button></a>
                                <a href="post-confirm-delete.jsp"><button type="submit" id="delete-post-btn" name="action" value="delete">Delete</button></a>
                            </c:if>
                                
                            <hr>
                            <a>
                                <h2>${post.getTitle()}</h2>
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

                                <a href="comments?post-id=${post.getId()}" class="comment">
                                    <i class="bi bi-chat"></i><span class="commentInsight">${post.getCommentCount()}</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
                            
    <script src="javascript/readMore.js"></script>
    <script src="javascript/insight.js"></script>
                                            
<jsp:include page="includes/endHtml.jsp" />
