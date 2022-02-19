
package ma.privatechat;

import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.HashMap;
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

    static Map<Integer, User> users = new HashMap<>();
    static ArrayList<Object[]> messages = new ArrayList<>();
    static Map<String, Session> onlineUsers = new HashMap<>();
    static Map<String, String> senderReceiver = new HashMap<>();
    static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());  
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) { 
        // getting the id of the current user from the session using a configurator
        String userID = (String) conf.getUserProperties().get("userID");
        User user = UserDB.getUser("select * from user where id = " + userID);
        session.getUserProperties().put(userID, userID);
        onlineUsers.put(userID, session);
        users.put(user.getId(), user);
        sessions.add(session);

        String receiverID = senderReceiver.get(userID);
        Session s = onlineUsers.get(userID);
        
        // receiver container
        if(s != null){
            try {
                s.getBasicRemote().sendText(toJson(UserDB.getUser("select * from user where id = " + receiverID), messages));
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(receiverID != null){
            User receiver = UserDB.getUser("select * from user where id = " + receiverID);
            try {
                session.getBasicRemote().sendText(toJson(receiver)); 
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // sends list of online users to all connected users
        Iterator<Session> iterator = sessions.iterator();
        while(iterator.hasNext()){
            try {
                iterator.next().getBasicRemote().sendText(toJson(users, messages)); 
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        Data data = new Gson().fromJson(message, Data.class);
        String senderID = data.getSenderID();
        String receiverID = data.getReceiverID();
        Session s = onlineUsers.get(receiverID) ;
        User sender = UserDB.getUser("select * from user where id = " + senderID);
        User receiver = UserDB.getUser("select * from user where id = " + receiverID);
        
        // when the current user clicks on an online user
        if(data.getMsg() == null){
            try {
                senderReceiver.put(senderID, receiverID);
                session.getBasicRemote().sendText(toJson(receiver, messages));
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        // when the current user send a message  
        } else{
            Date date = new Date(); 
            String time = new SimpleDateFormat("HH:mm").format(date);

            if (s != null) {
                messages.add(new Object[]{sender, receiver, data.getMsg(), time});
                try {
                    session.getBasicRemote().sendText(toJson(messages));
                    s.getBasicRemote().sendText(toJson(messages));
                } catch (IOException ex) {
                    Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @OnClose
    public void onClose(Session session){
        String id = (String) session.getUserProperties().get("userID");
        users.remove((Integer.parseInt(id)));
        senderReceiver.remove(id);
        sessions.remove(session);
        onlineUsers.remove(id);
        
        Iterator<Session> iterator = sessions.iterator();
        while(iterator.hasNext()){
            try {
                iterator.next().getBasicRemote().sendText(toJson(users)); // users
            } catch (IOException ex) {
                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @OnError
    public void onError(Throwable t){}
    
    public String toJson(Map<Integer, User> users){
        Data data = new Data(users);
        return new Gson().toJson(data);
    }
    
    public String toJson(User user){
        Data data = new Data(user);
        return new Gson().toJson(data);
    }
    
    public String toJson(User user, String msg, String time){
        Data data = new Data(user, msg, time);
        return new Gson().toJson(data);
    }
    
    public String toJson(ArrayList<Object[]> messages){
        Data data = new Data(messages);
        return new Gson().toJson(data);
    } 
    
    public String toJson(Map<Integer, User> users, ArrayList<Object[]> messages){
        Data data = new Data(users, messages);
        return new Gson().toJson(data);
    } 
    
    public String toJson(User user, ArrayList<Object[]> messages){
        Data data = new Data(user, messages);
        return new Gson().toJson(data);
    } 
}


/* first commmented snippet */

//static ArrayList<Message> messages;
//static Set<Session> chatUsers = Collections.synchronizedSet(new HashSet<>());    
//    @OnOpen
//    public void onOpen(Session session, EndpointConfig conf) {
//        System.out.println(this);
//        String senderID = (String) conf.getUserProperties().get("senderID");
//        String receiverID = (String) conf.getUserProperties().get("receiverID");
//
//        session.getUserProperties().put(receiverID, receiverID);
//        session.getUserProperties().put(senderID, senderID);
//        
//        User sender = UserDB.getUser("select * from user where id = " + senderID);
//        User receiver = UserDB.getUser("select * from user where id = " + receiverID);
//
//        String query = String.format("select * from message where sender_id = %s or sender_id = %s", senderID, receiverID);
//        messages = MessageDB.getAll(query);
//        
//        chatUsers.add(session);
//        Iterator<Session> iterator = chatUsers.iterator();
//        while (iterator.hasNext()) {
//            try {
//                iterator.next().getBasicRemote().sendText(buildJsonData(messages, sender, receiver));
//            } catch (IOException ex) {
//                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        displayUsers(); 
//    } 
//    
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        Date date = new Date();
//        String timS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//        String timeStamp = new SimpleDateFormat("HH:mm").format(date);
//        
//        Data data = new Gson().fromJson(message, Data.class);
//        data.setTime(timeStamp);
//        
//        User user = UserDB.getUser("select * from user where id = " + data.getSenderID());
//        User receiver = UserDB.getUser("select * from user where id = " + data.getReceiverID());
//        
//        messages.add(new Message(user, receiver, data.getMsg(), timeStamp));
//        MessageDB.insert(new Message(user, receiver, data.getMsg(), timS));
//        
//        Iterator<Session> iterator = chatUsers.iterator();
//        while(iterator.hasNext()){
//            try {
//                iterator.next().getBasicRemote().sendText(buildJsonData(messages, user, receiver));
//            } catch (IOException ex) {
//                Logger.getLogger(PrivateChatServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//    
//    @OnClose
//    public void onClose(Session session){
//        messages.clear();
//        chatUsers.remove(session);
//    }
//    
//    @OnError
//    public void onError(Throwable t){
//        
//    }
//    
//    private String buildJsonData(ArrayList<Message> messages,User sender, User receiver) {
//        return new Gson().toJson(new Data(messages, sender, receiver));
//    }
//    
//    public void displayUsers(){
//        for(Session session:chatUsers){
//            System.out.println(session.getUserProperties().keySet() + " " + session.getUserProperties().values());
//        }
//    }
