
package ma.privatechat;

import java.util.ArrayList;

import ma.business.User;
import ma.business.Message;


public class Data {

    private ArrayList<Message> messages;
    private User sender, receiver;
    private String time, msg, senderID, receiverID;
    
    public Data() {
    
    }

    public Data(ArrayList<Message> messages, User sender, User receiver) {
        this.messages = messages;
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public Data(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }
    
}