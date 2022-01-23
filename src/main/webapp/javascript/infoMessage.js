//function login(form) {
//    var xhttp = new XMLHttpRequest();
//    
//    var FORM = document.getElementById("login-form");
//    
//    function handleForm(event) {
//        event.preventDefault();
//    }
//    
//    FORM.addEventListener('submit', handleForm);
//    
//    // getting the user and pass 
//    let username = document.querySelector(".username").textContent;
//    let password = document.querySelector(".password").textContent;
//    
//    var formData = new FormData();
//    formData.append("username", username);
//    formData.append("password", password);
//    
//    xhttp.onreadystatechange = function () {
//        
//        if(this.readyState === 4 && this.status === 200) {
//            
//            let msg = this.responseData;
//            console.log(this.responseData);
//            
//            if(msg === "valide"){
//                document.querySelector(".done-message").style.display = "block" ;
//                document.querySelector(".error-message").style.display = "none" ;
//            } else{
//                document.querySelector(".error-message").style.display = "block" ;
//                document.querySelector(".done-message").style.display = "none" ;
//            }
//            
//        }
//        
//    };
//
//    xhttp.open("POST", "http://localhost:8090/blog/login", true);
//    xhttp.send(formData);
//
//};