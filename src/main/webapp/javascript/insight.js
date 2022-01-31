function likeInsight(icon){
    let insight = icon.parentElement;
    let postDesc = insight.parentElement;
    let post = postDesc.parentElement;
    let id = post.querySelector(".post-hidden-id").textContent;
    
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
    
    xhttp.open("GET", `http://localhost:8090/blog/post-insights?action=like&post-id=${id}`, true);
    xhttp.send();
    
};

function dislikeInsight(icon){
    let insight = icon.parentElement;
    let postDesc = insight.parentElement;
    let post = postDesc.parentElement;
    let id = post.querySelector(".post-hidden-id").textContent;
    
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
    
    xhttp.open("GET", `http://localhost:8090/blog/post-insights?action=dislike&post-id=${id}`, true);
    xhttp.send();
    
};


// comment insight

function commentLike(icon){
    
    let insight = icon.parentElement;
    let commentBody = insight.parentElement;
    let id = commentBody.querySelector(".comment-hidden-id").textContent;
    console.log(id);
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            insight.querySelector("#comment-like").innerHTML = this.responseText.split(",")[0];
            insight.querySelector("#comment-dislike").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/comment-insights?action=like&comment-id=${id}`, true);
    xhttp.send();
};

function commentDislike(icon){
    
    let insight = icon.parentElement;
    let commentBody = insight.parentElement;
    let id = commentBody.querySelector(".comment-hidden-id").textContent;
    console.log(id);
    
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            insight.querySelector("#comment-like").innerHTML = this.responseText.split(",")[0];
            insight.querySelector("#comment-dislike").innerHTML = this.responseText.split(",")[1];
        }
     };    
    
    xhttp.open("GET", `http://localhost:8090/blog/comment-insights?action=dislike&comment-id=${id}`, true);
    xhttp.send();
};