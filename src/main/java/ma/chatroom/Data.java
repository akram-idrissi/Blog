
package ma.chatroom;


import java.util.ArrayList;
import java.util.Map;
import ma.business.User;

public class Data {
    
    Map<String, User> users;
    ArrayList<Object[]> items;
    
    public Data(ArrayList<Object[]> items) {
        this.items = items;
    }

    public Data(Map<String, User> users, ArrayList<Object[]> items) {
        this.users = users;
        this.items = items;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public ArrayList<Object[]> getItems() {
        return items;
    }

    public void setItems(ArrayList<Object[]> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Data{" + "users=" + users + ", items=" + items + '}';
    }
    
}
