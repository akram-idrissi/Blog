package ma.business;

public class Message  implements java.io.Serializable {

    private Integer id;
    private User senderId;
    private User receiverId;
    private String messageDate;

    public Message() {
    }

    public Message(Integer id, User senderId, User receiverId, String messageDate) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageDate = messageDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", messageDate=" + messageDate + '}';
    }
    
}


