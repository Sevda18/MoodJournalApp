# Mood Journal App

## Overview
The **Mood Journal App** is a console-based application built in **Java** that allows users to track and manage their moods.  
The application uses a **MySQL database** to store user accounts and mood entries.

---

## Features

### User
- Add a mood entry:  
  - Name  
  - Notes  
  - Category (HAPPY, SAD, ANGRY, CALM, EXCITED, TIRED, STRESSED)  
  - Date and time are recorded automatically  

- Edit a mood entry:  
  - Update notes for an existing mood  

- View moods:  
  - List all moods for the user in chronological order  

---

## Architecture

- **model** → `User`, `Mood`, `Role`, `MoodCategory`  
- **repository** → `UserRepository`, `MoodRepository` (SQL queries to the database)  
- **service** → `UserService`, `MoodService` (business logic, validation)  
- **utils** → `DBConnection` (database connection handler)  
- **main** → `Main` (console menu for registration, login, and user operations) 
