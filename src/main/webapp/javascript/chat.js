var userID = document.getElementById("hidden-id").value;
var socket = new WebSocket("ws://localhost:8090/blog/chat-room.jsp"); 
    
if (!'WebSocket' in window) {
    alert('WebSocket is not supported by this browser.');
} else {
    socket.onopen = function(response){
        console.log("connection established");
    };

    socket.onerror = function(response){
        console.log("ERROR");
    };

    socket.onmessage = function(response){
        var json = JSON.parse(response.data);
        console.log(json);
        peopleList(json.users);
        messageList(json.items);
    };

    socket.onclose = function (response) {
        console.log("connection closed");
    };

    function send() {
        var message = document.getElementById("form-control");
        socket.send(message.value);
        message.value = "";
    }
}

function peopleList(users){
    var userContainer = document.querySelector(".border-right");
    userContainer.innerHTML = "";
    for(var user in users){
        var data = users[user];
        var person = 
                `<div class="list-group-item list-group-item-action border-0">
                    <div class="d-flex align-items-start">
                        <img src="images/${data["image"]}" class="rounded-circle mr-1" alt="Vanessa Tucker" width="40" height="40">
                        <div class="flex-grow-1 ml-3">
                            ${data["username"]}
                        </div>
                    </div>
                </div>`;
        
        userContainer.innerHTML += person;
    };
};

function messageList(items){
    
    var message;
    var messageContainer = document.querySelector(".chat-messages");
    messageContainer.innerHTML = "";
    for(var item in items){
        var user = items[item][0];
        var msg = items[item][1];
        var time = items[item][2];
        
        if(userID == user["id"]){
            message = `
                    <div class="chat-message-right pb-4">
                        <div>
                            <img src="images/${user["image"]}" class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                            <div class="text-muted small text-nowrap mt-2">${time}</div>
                        </div>
                        <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                            <div class="font-weight-bold mb-1">You</div>
                            ${msg}
                        </div>
                    </div>`;

        } else{
            message = 
                    `
                    <div class="chat-message-left pb-4">
                        <div>
                            <img src="images/${user["image"]}" class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                            <div class="text-muted small text-nowrap mt-2">${time}</div>
                        </div>
                        <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                            <div class="font-weight-bold mb-1">${user["username"]}</div>
                            ${msg}
                        </div>
                    </div>`;
        };
        messageContainer.innerHTML += message;
    };
    
};
