function seeMore(){
  
  var xhttp = new XMLHttpRequest();
  
  var date = document.querySelector(".hidden-date").textContent;
  var title = document.getElementsByTagName("h2")[1].textContent;
  
  xhttp.onreadystatechange = function(){
      
    xhttp.onreadystatechange = function() {

        if(this.readyState === 4 && this.status === 200) {
            var json = JSON.parse(this.responseText);
            console.log(json);
            if(json.length === 0 || json === null){
                document.querySelector(".see-more").style.display = "none";
            } else{
            
                for(let j in json){
                    
                    let comment = 
                            `
                                <li class="comment-page">
                                    <div class="padding">
                                        <div class="comment-body">
                                            <img src="images/${json[j]["user"]["image"]}" class="avatar" alt="">
                                            <div class="user-info">
                                                <p class="meta">${json[j]["commentDate"]}&#160&#160<a href="#">${json[j]["user"]["username"]}</a> says : 
                                                    <i class="pull-right">
                                                        <a href="#">
                                                            <span class="update-comment"><i class="bi bi-arrow-repeat"></i></span>
                                                            <span class="delete-comment" ><i class="bi bi-trash"></i></span>
                                                        </a>
                                                    </i>
                                                </p>

                                                <hr class="break-line">
                                                <p class="content" class="comment-text">
                                                    ${json[j]["content"]}
                                                </p>
                                                <span onclick="readMore(this)" class="read-more-btn">Read More</span>
                                            </div>

                                            <div class="insights">
                                                <a onclick="likeInsight(this)" href="javascript:void(0);" class="like">
                                                    <i class="bi bi-hand-thumbs-up"></i><span class="likeInsight">${json[j]["likeCount"]}</span>
                                                </a>

                                                <a href="comments?post-date=${json[j]["post"]["postedDate"]}&title=${json[j]["post"]["title"]}" class="dislike">
                                                    <i class="bi bi-hand-thumbs-down"></i> <span class="dislikeInsight">${json[j]["dislikeCount"]}</span>
                                                </a>
                                            </div>

                                        </div>
                                    </div>
                                </li>
                            `;

                        document.querySelector(".comments").innerHTML += comment;

                }
            }
            
        }
    };    
      
  };
  
  xhttp.open("GET", `comments?post-date=${date}&title=${title}&see-more=true`, true);
  xhttp.send();
    
};