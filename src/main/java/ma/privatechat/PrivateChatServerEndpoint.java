
package ma.privatechat;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Collections;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import java.text.SimpleDateFormat;
import javax.websocket.EndpointConfig;
import javax.websocket.server.ServerEndpoint;


import ma.data.UserDB;
import ma.business.User;
import ma.data.MessageDB;
import ma.business.Message;


@ServerEndpoint(
        value="/chat.jsp", 
        configurator = PrivateChatServerConfigurator.class
)
public class PrivateChatServerEndpoint {
    
    static ArrayList<Message> messages;
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<>());
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) {
        String recID = (String) conf.getUserProperties().get("recID");
        String userID = (String) conf.getUserProperties().get("userID");

        session.getUserProperties().put("userID", userID);
        session.getUserProperties().put("recID", recID);
        
        User sender = UserDB.getUser("select * from user where id = " + userID);
        User receiver = UserDB.getUser("select * from user where id = " + recID);

        String query = String.format("select * from message where sender_id = %s and receiver_id = %s", userID, recID);
        messages = MessageDB.getAll(query);
        
        chatroomUsers.add(session);
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                iterator.next().getBasicRemote().sendText(buildJsonData(messages, sender, receiver));
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    @OnMessage
    public void onMessage(String message, Session session) {
        Date date = new Date();
        String timS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        String timeStamp = new SimpleDateFormat("HH:mm").format(date);
        
        Data data = new Gson().fromJson(message, Data.class);
        data.setTime(timeStamp);
        
        User user = UserDB.getUser("select * from user where id = " + data.getSenderID());
        User receiver = UserDB.getUser("select * from user where id = " + data.getReceiverID());
        
        messages.add(new Message(user, receiver, data.getMsg(), timeStamp));
        MessageDB.insert(new Message(user, receiver, data.getMsg(), timS));
        
        Iterator<Session> iterator = chatroomUsers.iterator();
        while(iterator.hasNext()){
            try {
                iterator.next().getBasicRemote().sendText(buildJsonData(messages, user, receiver));
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @OnClose
    public void onClose(Session session){
        chatroomUsers.remove(session);
    }
    
    @OnError
    public void onError(Throwable t){
        
    }
    
    private String buildJsonData(ArrayList<Message> messages,User sender, User receiver) {
        return new Gson().toJson(new Data(messages, sender, receiver));
    }
    
} 
