function addComment(btn){
    
    var FORM = document.getElementById("add-comment-form");
    
    function handleForm(event) {
        event.preventDefault();
    }
    
    FORM.addEventListener('submit', handleForm);
    
    let date = document.querySelector('[name="date"]').value;
    let title = document.querySelector('[name="title"]').value;
    let content = document.querySelector('[name="content"]').value;
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            
        }
    };
    
    xhttp.open("GET", `http://localhost:8090/blog/comment-action?action=add&date=${date}&title=${title}&content=${content}`, true);
    xhttp.send();
    
}