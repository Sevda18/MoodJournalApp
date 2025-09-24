package model;

import java.time.LocalDateTime;

public class Mood {
    private int id;
    private int userId;
    private String name;
    private String note;
    private LocalDateTime dateTime;
    private MoodCategory moodCategory;
    private static int idCounter;

    public Mood(int userId, String name, String note, MoodCategory m){
        this.id = getNextId();
        this.userId = userId;
        this.name = name;
        setNotes(note);
        this.dateTime = LocalDateTime.now();
        setMoodCategory(m);
    }

    public Mood(int userId, String name, LocalDateTime date, String note, MoodCategory m){
        this.id = getNextId();
        this.userId = userId;
        this.name = name;
        this.dateTime = date;
        setNotes(note);
        this.dateTime = LocalDateTime.now();
        setMoodCategory(m);
    }

    public Mood(int id, int userId, String name, LocalDateTime date, String note, MoodCategory m){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.dateTime = date;
        setNotes(note);
        this.dateTime = LocalDateTime.now();
        setMoodCategory(m);
    }

    private int getNextId(){
        return ++idCounter;
    }

    public int getId(){
        return id;
    }

    public int getUserId(){
        return userId;
    }

    public String getName(){
        return name;
    }

    public String getNote(){
        return note;
    }

    public MoodCategory getMoodCategory(){
        return moodCategory;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setNotes(String newNotes){
        this.note = newNotes;
    }

    public void setMoodCategory(MoodCategory newCategory){
        this.moodCategory = newCategory;
    }
}
