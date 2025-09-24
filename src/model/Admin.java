package model;

import java.util.List;

public class Admin extends User{
    public Admin(String name, String email, String password){
        super(name, email, password, Role.ADMIN);
    }

    public void viewAllUsers(List<User> users){
        for(User user : users){
            System.out.println(user.getId() + " " + user.getUsername() + " " + user.getEmail() + " " + user.getRole());
        }
    }

    public void deleteUser(List<User> users, User userToDelete){
        users.remove(userToDelete);
    }

    public void generateReport(List<User> users){
        System.out.println("User report: ");
        for(User u: users)
        {
            System.out.println(u.getUsername() + " "+ u.getEmail());
        }
    }
}
