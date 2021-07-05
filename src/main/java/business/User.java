package business;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    public static int nrOrders;

    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
        nrOrders = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
