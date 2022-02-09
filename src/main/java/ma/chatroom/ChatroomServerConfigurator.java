
package ma.chatroom;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;


public class ChatroomServerConfigurator extends ServerEndpointConfig.Configurator {
    
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        int userId = (int) session.getAttribute("user");
        sec.getUserProperties().put("userID", userId + "");
    }
    
    
}