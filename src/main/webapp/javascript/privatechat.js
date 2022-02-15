let count = 0;
var userID = document.getElementById("hidden-user").value;
var socket = new WebSocket("ws://localhost:8090/blog/chat.jsp"); 
  

socket.onopen = function (response) {
//    document.getElementById("p-4").value = "";
//    document.getElementById("col-lg-6").value = "";
//    var id = document.getElementById("receiver-id");
//    console.log(id);
//    if(id != null || id != undefined){
//        sendReceiver(id.value);
//        console.log("send id ");
//    }
    console.log("connection established");

};

socket.onerror = function(response){
    console.log("ERROR");
};

socket.onmessage = function (response) {
    var json = JSON.parse(response.data);
    console.log(json);
    
    if(json.messages !== undefined)
        messageList(json.messages);

    if(json.users !== undefined)
        peopleList(json.users);

    if(json.receiver !== undefined)
        addReceiver(json.receiver);
};

socket.onclose = function(response) {
    console.log("connection closed");
};

function send() {
    var id = document.getElementById("receiver-id");
    var message = document.getElementById("form-control");
    if( id !== null){
        var json = JSON.stringify({
                    "msg":message.value,
                    "senderID":userID,
                    "receiverID":id.value 
                });

        socket.send(json);
        message.value = "";
    }
}

function sendReceiver(id) {
    var json = JSON.stringify({"senderID":userID, "receiverID":id});
    socket.send(json);
}

function addReceiver(receiver){
    var messageContainer = document.getElementById("p-4");
    messageContainer.innerHTML = "";
    var body = document.getElementsByTagName("body")[0];
    body.innerHTML += `<input id="receiver-id" type="hidden" value="${receiver["id"]}" >`;
    var receiverContainer = document.getElementById("d-flex");
    receiverContainer.innerHTML = "";
    var person = 
            `
            <div id="col-lg-6">
                <a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                    <input id="container-receiver-id" type="hidden" value="${receiver["id"]}" >
                    <img src="images/${receiver["image"]}" alt="avatar">
                </a>
                <div class="chat-about">
                    <h6 class="m-b-0">${receiver["username"]}</h6>
                    <small>Last seen: 2 hours ago</small>
                </div>
            </div>
`;

    receiverContainer.innerHTML += person;
};

function messageList(items){
    
    var message;
    var messageContainer = document.getElementById("p-4");
    var receiverID = document.getElementById("receiver-id");
    var receiverContainer = document.getElementById("container-receiver-id");
    messageContainer.innerHTML = "";
    
//    if(receiverContainer === undefined || receiverContainer === null || receiverID === undefined || receiverID === null){
//        var notif = document.getElementById("notif-count");
//        if(notif !== null){
//            notif.innerHTML = count++;
//        } else if(count > 0){
//            notif.innerHTML = count++;
//            document.querySelector(".notif").style.display = 'block';
//        }
//        
//    } 
    
    for(var item in items){
        var sender = items[item][0];
        var receiver = items[item][1];
        var msg = items[item][2];
        var time = items[item][3];
        
        if(receiverContainer !== null){
        
            if((sender["id"] == userID && receiver["id"] == receiverContainer.value) || (sender["id"] == receiverContainer.value && receiver["id"] == userID) ){

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
            }
        }
    }; 
};


function peopleList(users){
    var peopleContainer = document.getElementById("people-list");
    peopleContainer.innerHTML = "";

    for (let u in users) {
        var id = u;
        var user = users[u];

        if (id != userID) {
            var person =
                    `<a onclick="sendReceiver(${id})" href="#" class="list-group-item list-group-item-action border-0">
                        <div class="d-flex align-items-start">
                            <img src="images/${user["image"]}" class="rounded-circle mr-1" alt="Vanessa Tucker" width="40" height="40">
                            <div class="flex-grow-1 ml-3">
                                ${user["username"]}
                                <div class="notif">
                                    <p id="notif-count">${count}</p>
                                </div>
                            </div>
                        </div>
                    </a>`;

            peopleContainer.innerHTML += person;
        }
    }
}

