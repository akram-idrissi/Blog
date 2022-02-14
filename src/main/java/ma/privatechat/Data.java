
package ma.privatechat;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import ma.business.User;
import ma.business.Message;


public class Data { 
    
    private Map<Integer, User> users;
    private ArrayList<Object[]> messages;
    private User sender, receiver, toReceiver;
    private String time, msg, senderID, receiverID;
    
    public Data() {}
    
    public Data(Map<Integer, User> users) {
        this.users = users;
    }
    
    public Data(Map<Integer, User> users, ArrayList<Object[]> messages) {
        this.users = users;
        this.messages = messages;
    }
    
    public Data(User receiver, String msg, String time) {
        this.msg = msg;
        this.time = time;
        this.toReceiver = receiver;
    }
    
    public Data(ArrayList<Object[]> messages) {
        this.messages = messages;
    }
    
    public Data(User receiver) {
        this.receiver = receiver;
    }
    
    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
    
    public ArrayList<Object[]> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Object[]> messages) {
        this.messages = messages;
    }

    public User getToReceiver() {
        return toReceiver;
    }

    public void setToReceiver(User toReceiver) {
        this.toReceiver = toReceiver;
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