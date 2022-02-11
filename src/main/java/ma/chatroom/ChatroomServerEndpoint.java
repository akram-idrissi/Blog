
package ma.chatroom;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.Collections;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import java.text.SimpleDateFormat;
import javax.websocket.EndpointConfig;
import javax.websocket.server.ServerEndpoint;

import ma.data.UserDB;
import ma.business.User;


@ServerEndpoint(
        value="/chat-room.jsp", 
        configurator = ChatroomServerConfigurator.class
)
public class ChatroomServerEndpoint {
    
    static Map<String, User> users = new HashMap<>();
    static ArrayList<Object[]> items = new ArrayList<>();
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<>());
    
    @OnOpen
    public void onOpen(EndpointConfig config, Session session) {
        String userId = (String) config.getUserProperties().get("userID");
        User user = UserDB.getUser("select * from user where id = " + userId);
        session.getUserProperties().put("userID", userId);
        chatroomUsers.add(session);
        users.put(userId, user);
        
        Iterator<Session> iterator = chatroomUsers.iterator();
        while (iterator.hasNext()) {
            try {
                iterator.next().getBasicRemote().sendText(buildJsonData(users, items));
            } catch (IOException ex) {
                Logger.getLogger(ChatroomServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        String userID = (String) session.getUserProperties().get("userID");
        
        if(userID != null){
            String timeStamp = new SimpleDateFormat("HH:mm").format(new java.util.Date());
            User user = UserDB.getUser("select * from user where id = " + userID);
            items.add(new Object[]{user, message, timeStamp});
            
            Iterator<Session> iterator = chatroomUsers.iterator();
            while(iterator.hasNext()){
                try {
                    iterator.next().getBasicRemote().sendText(buildJsonData(users, items));
                } catch (IOException ex) {
                    Logger.getLogger(ChatroomServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    private String buildJsonData(ArrayList<Object[]> items) {
        return new Gson().toJson(new Data(items));
    }
    
    private String buildJsonData(Map<String, User> users, ArrayList<Object[]> items) {
        return new Gson().toJson(new Data(users, items));
    }

}
