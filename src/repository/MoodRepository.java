package repository;

import model.Mood;
import model.MoodCategory;

import utils.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MoodRepository {
    public void addMood(Mood mood, int userId)
    {
        if(mood.getDateTime().isAfter(LocalDateTime.now())){
            System.out.println("Mood date cannot be in the future.");
            return;
        }
        String sql = "INSERT INTO mood(user_id, name, date_time, category, notes ) VALUES(?,?,?,?, ?)";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, mood.getUserId());
            ps.setString(2, mood.getName());
            ps.setTimestamp(3, Timestamp.valueOf(mood.getDateTime()));
            ps.setString(4, mood.getMoodCategory().toString());
            ps.setString(5, mood.getNote());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Mood> getMoodsByUserId(int userId){
        List<Mood> moods = new ArrayList<>();
        String sql = "SELECT * FROM mood WHERE user_id = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDateTime dateTime = rs.getTimestamp("date_time").toLocalDateTime();
                MoodCategory category = MoodCategory.valueOf(rs.getString("category"));
                String notes = rs.getString("notes");
                Mood mood = new Mood(id, userId, name, dateTime, notes, category);
                moods.add(mood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moods;
    }

    public void updateMoodNotes(int moodId, String newNotes) {
        String sql = "UPDATE mood SET notes = ? WHERE id = ?";
        try(Connection c = DBConnection.getConnection();){
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, newNotes);
            ps.setInt(2, moodId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No mood found with ID: " + moodId);
            } else {
                System.out.println("Mood notes updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}











