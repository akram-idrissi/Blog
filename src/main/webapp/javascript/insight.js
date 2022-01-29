//window.onload = function() {
//    var xhttp = new XMLHttpRequest();
//    
//    posts = document.getElementsByClassName("post");
//    
//    for (var i = 0; i < posts.length; i++) {
//    
//        xhttp.onreadystatechange = function () {
//            posts[i].querySelector(".likeInsight").innerHTML = this.responseText.split(",")[0];
//            posts[i].querySelector(".dislikeInsight").innerHTML = this.responseText.split(",")[1];
//        };
//
//        var date = posts[i].querySelector(".hidden-date").textContent;
//        
//        
//        xhttp.open("GET", "http://localhost:8090/blog/post-insights?date=" + date, false);
//        xhttp.send();
//        
//    }
//};

function likeInsight(icon){
    let insight = icon.parentElement;
    let postDesc = insight.parentElement;
    let post = postDesc.parentElement;
    let date = post.querySelector(".hidden-date").textContent;
    let title = postDesc.getElementsByTagName("h2")[0].textContent;
    
    
    let likeIcon = insight.querySelector(".like");
    let dislikeIcon = insight.querySelector(".dislike");
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            likeIcon.querySelector(".likeInsight").innerHTML = this.responseText.split(",")[0];
            dislikeIcon.querySelector(".dislikeInsight").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/post-insights?action=like&date=${date}&title=${title}`, true);
    xhttp.send();
    
};

function dislikeInsight(icon){
    let insight = icon.parentElement;
    let postDesc = insight.parentElement;
    let post = postDesc.parentElement;
    let date = post.querySelector(".hidden-date").textContent;
    let title = postDesc.getElementsByTagName("h2")[0].textContent;
    
    let likeIcon = insight.querySelector(".like");
    let dislikeIcon = insight.querySelector(".dislike");
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            likeIcon.querySelector(".likeInsight").innerHTML = this.responseText.split(",")[0];
            dislikeIcon.querySelector(".dislikeInsight").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/post-insights?action=dislike&date=${date}&title=${title}`, true);
    xhttp.send();
    
};


// comment insight

function commentLike(icon){
    
    let insight = icon.parentElement;
    let commentBody = insight.parentElement;
    let date = commentBody.querySelector(".comment-hidden-date").textContent;
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            document.getElementById("comment-like").innerHTML = this.responseText.split(",")[0];
            document.getElementById("comment-dislike").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/comment-insight?action=like&date=${date}`, true);
    xhttp.send();
};

function commentDislike(icon){
    
    let insight = icon.parentElement;
    let commentBody = insight.parentElement;
    let date = commentBody.querySelector(".comment-hidden-date").textContent;
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            document.getElementById("comment-like").innerHTML = this.responseText.split(",")[0];
            document.getElementById("comment-dislike").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/comment-insight?action=dislike&date=${date}`, true);
    xhttp.send();
};