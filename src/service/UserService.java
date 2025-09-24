package service;

import model.Mood;
import model.MoodCategory;
import model.User;
import model.Role;
import repository.MoodRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final UserRepository userRepo= new UserRepository();
    private final MoodRepository moodRepo = new MoodRepository();

    public void registerUser(String username, String email, String password){
        if(userRepo.findByUsername(username) != null){
            System.out.println("Username is already taken. Please choose another one.");
            return;
        }
        if(userRepo.findByEmail(email) != null){
            System.out.println("User with email " + email + " already exists.");
            return;
        }

        User newUser = new User(username, email, password, Role.USER);
        userRepo.addUser(newUser);
        System.out.println("Registration is successful!");
    }

    public User loginUserWithEmail(String email, String password){
       User user = userRepo.findByUsername(email);
       if(user != null)
       {
           if(!user.getPassword().equals(password)){
               System.out.println("Invalid username/email or password.");
               return null;
           }
           System.out.println("Login is successful. Welcome " + user.getUsername());
           return user;
       }
       else{
              System.out.println("There is no user with this username or email." +
                        " Please register first.");
              return null;
       }
    }

    public User loginUserWithUsername(String username, String password){
        User user = userRepo.findByUsername(username);
        if(user != null)
        {
            if(!user.getPassword().equals(password)){
                System.out.println("Invalid username/email or password.");
                return null;
            }
            System.out.println("Login is successful. Welcome " + user.getUsername());
            return user;
        }
        else{
            System.out.println("There is no user with this username or email." +
                    " Please register first.");
            return null;
        }
    }

    public void addMood(User user, String name, String notes, MoodCategory moodType){
        Mood m = new Mood(user.getId(), name, notes, moodType);
        moodRepo.addMood(m, user.getId());
    }

    public void addMood(User user, String name, LocalDateTime d, String notes, MoodCategory moodType){
        Mood m = new Mood(user.getId(), name, d,  notes, moodType);
        moodRepo.addMood(m, user.getId());
    }

    public List<Mood> findAllByUser(User user){
        return moodRepo.getMoodsByUserId(user.getId());
    }

    public void editMood(int moodId, String newNotes)
    {
        moodRepo.updateMoodNotes(moodId, newNotes);
    }

    public List<Mood> getMoodByUserId(int userId){
        return moodRepo.getMoodsByUserId(userId);
    }
}
