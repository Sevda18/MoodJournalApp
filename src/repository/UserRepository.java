package repository;

import model.Role;
import model.User;

import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public void addUser(User user){
        if(existsByEmail(user.getEmail())){
            System.out.println("User with email " + user.getEmail()+ " already exists.");
            return;
        }
        String sql = "INSERT INTO users(username, email, password, role) VALUES(?,?,?,?)";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().toString());
            System.out.println("👉 Executing SQL: " + sql);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Потребителят е добавен успешно.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsByEmail(String email){
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void deleteUser(int userId){
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Connection c = DBConnection.getConnection();){
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                User user = new User(username, email, password, role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User findByEmailAndPassword(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String username = rs.getString("username");
                Role role = Role.valueOf(rs.getString("role"));
                return new User(username, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findByUsernameAndPassword(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                String email = rs.getString("email");
                Role role = Role.valueOf(rs.getString("role"));
                return new User(username, email, password, role);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public User findByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String email = rs.getString("email");
                String password = rs.getString("password");

                String roleStr = rs.getString("role");
                Role role = Role.valueOf(rs.getString("role").toUpperCase());


                return new User(username, email, password, role);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String username = rs.getString("email");
                String password = rs.getString("password");
                Role role = Role.valueOf(rs.getString("role"));
                return new User(username, email, password, role);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
