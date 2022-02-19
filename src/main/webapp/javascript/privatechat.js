let count = 1;
var userID = document.getElementById("hidden-user").value;
var socket = new WebSocket("ws://localhost:8090/blog/chat.jsp"); 
  

socket.onopen = function (response) {
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
    
    if(json.messages !== undefined)
        displayNotification(json.messages);

};

socket.onclose = function(response) {
    count = 0;
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
    count = 1;
    let notif = document.querySelector(`.notif-${id}`);
    if(notif != null)
        notif.style.display = "none";
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
    
    for(var item in items){
        let sender = items[item][0];
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
                            </div>
                        `;

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
                    `<a  id="list-group-item" onclick="sendReceiver(${id})" href="#" class="list-group-item list-group-item-action ">
                        <div class="d-flex align-items-start">
                            <input type="hidden" class="pple-id" value="${user["id"]}" >
                            <img src="images/${user["image"]}" class="rounded-circle mr-1" alt="Vanessa Tucker" width="40" height="40">
                            <div class="flex-grow-1 ml-3">
                                ${user["username"]}
                                <div class="notif notif-${id}">
                                    <p id="notif-count">${count}</p>
                                </div>
                            </div>
                        </div>
                    </a>`;

            peopleContainer.innerHTML += person;
        }
    }
}

function getPeopleIds(){
    let ids = [];
    var peopleContainer = document.getElementById("people-list");
    let inputs = peopleContainer.getElementsByTagName("input");
    for(let i = 0;i < inputs.length; i++){
        ids.push(inputs[i].value);
    }
    return ids;
}

function displayNotification(messages){
    
    let lastMsg; 
    let ids = getPeopleIds();
    if(messages.length > 0){
        lastMsg = messages[messages.length - 1];
        let sender = lastMsg[0];
        var receiverContainer = document.getElementById("container-receiver-id");
        
        if(receiverContainer == null || receiverContainer.value == sender["id"]){
            for(let i = 0;i < ids.length; i++){
                console.log(ids[i]);
                if(ids[i] == sender["id"]){
                    let notif = document.querySelector(`.notif-${ids[i]}`);
                    count ++;
                    notif.style.display = "block";
                    break;
                }
            }
        }
    }
}
