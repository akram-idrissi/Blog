window.onload = function() {
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function () {
        
        
        if(this.readyState === 4 && this.status === 200) {
            
            let json = JSON.parse(this.responseText);
            let img = json["image"];
            let email = json["email"];
            let usn = json["username"];
            
            document.querySelector(".profile-username").textContent = usn;
            document.querySelector(".profile-email").textContent = email;
            
            document.querySelector(".profile-username-in").value = usn;
            document.querySelector(".profile-email-in").value = email;
            
            document.getElementById("img-path").textContent = "/" + img;
            document.querySelector(".profile-image").src = "images/" + img;
        }
        
        
    };

    xhttp.open("GET", "http://localhost:8090/blog/profile", true);
    xhttp.send();

};

function profile(btn){
    
    var form = document.getElementById("profile-form");
    function handleForm(event) {
        event.preventDefault();
    }
    form.addEventListener('submit', handleForm);
    let selectedImg;
    
    var file = document.getElementById('input-img').files[0];
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
            
            let json = JSON.parse(this.responseText);
            let img = json["image"];
            let email = json["email"];
            let usn = json["username"];
            
            document.querySelector(".profile-username").textContent = usn;
            document.querySelector(".profile-email").textContent = email;
            
            document.querySelector(".profile-username-in").value = usn;
            document.querySelector(".profile-email-in").value = email;
            
            document.getElementById("img-path").textContent = "/" + img;
            document.querySelector(".profile-image").src = "images/" + img;
            
        }
     };    
    
    xhttp.open("POST", "http://localhost:8090/blog/edit-profile", true);
    xhttp.send("image="+file);
    
};