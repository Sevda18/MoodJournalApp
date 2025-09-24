# Mood Journal App

## Overview
The **Mood Journal App** is a console-based application built in **Java** that allows users to track and manage their moods.  
The application uses a **MySQL database** to store user accounts and mood entries.  

It supports two roles:
- **Regular User** – manages only their own moods.  
- **Administrator** – manages users and can generate reports.  

---

## Features

### Regular User
- **Add a mood entry**  
  - Name  
  - Notes  
  - Category (HAPPY, SAD, ANGRY, CALM, EXCITED, TIRED, STRESSED)  
  - Date and time are recorded automatically  

- **Edit a mood entry**  
  - Update notes for an existing mood  

- **View moods**  
  - Display all moods for the current user in chronological order  

---

### Administrator
- **View all users**  
  - Display a list of all registered users with their details  

- **Delete a user**  
  - Remove a user by ID (all related moods are also deleted via `ON DELETE CASCADE`)  

---

## Architecture

- **model** → `User`, `Admin`, `Mood`, `Role`, `MoodCategory`  
- **repository** → `UserRepository`, `MoodRepository` (SQL queries to the database)  
- **service** → `UserService`, `AdminService`, `MoodService` (business logic, validation)  
- **utils** → `DBConnection` (database connection handler)  
- **main** → `Main` (console menu for registration, login, and role-based operations)
