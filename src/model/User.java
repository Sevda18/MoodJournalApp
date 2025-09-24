package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Role role;
    static private int idCounter;

    public User(String username, String email, String password, Role role){
        this.id = nextID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private int nextID(){
        return ++idCounter;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public Role getRole() {
        return role;
    }

    public int getId(){
        return id;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return getId() + " "+ getUsername() + " " + getEmail() + " " + getRole();
    }
}
