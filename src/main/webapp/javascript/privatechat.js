var userID = document.getElementById("hidden-user").value;
var recID = document.getElementById("hidden-receiver").value;
var socket = new WebSocket("ws://localhost:8090/blog/chat.jsp"); 
    

socket.onopen = function(response){
    console.log("connection established");
};

socket.onerror = function(response){
    console.log("ERROR");
};

socket.onmessage = function(response){
    var json = JSON.parse(response.data);
    console.log(json);
    
    addReceiver(json.receiver);
    messageList(json.messages);
};

socket.onclose = function(response) {
    console.log("connection closed");
};

function send() {
//    let now = new Date();
//    var time = moment(now).format('HH:MM');
    var message = document.getElementById("form-control");
    
    var json = JSON.stringify({
                "senderID":userID,
                "receiverID":recID,
                "msg":message.value
            });
    
    socket.send(json);
    message.value = "";
}

function addReceiver(receiver){
    var receiverContainer = document.getElementById("col-lg-6");
    receiverContainer.innerHTML = "";
    var person = 
            `<a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                <img src="images/${receiver["image"]}" alt="avatar">
            </a>
            <div class="chat-about">
                <h6 class="m-b-0">${receiver["username"]}</h6>
                <small>Last seen: 2 hours ago</small>
            </div>`;

    receiverContainer.innerHTML += person;
};

function messageList(items){
    var message;
    var messageContainer = document.getElementById("p-4");
    for(var item in items){
        var msg = items[item]["msg"];
        var sender = items[item]["senderId"];
        var time = items[item]["messageDate"];
        
        if(userID == sender["id"]){
            message = `
                    <div class="chat-message-right pb-4">
                        <div>
                            <img src="images/${sender["image"]}" class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
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
                            <img src="images/${sender["image"]}" class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                            <div class="text-muted small text-nowrap mt-2">${time}</div>
                        </div>
                        <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                            <div class="font-weight-bold mb-1">${sender["username"]}</div>
                            ${msg}
                        </div>
                    </div>`;
        };
        messageContainer.innerHTML += message;
    }; 
};
