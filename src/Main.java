import model.Mood;
import model.MoodCategory;
import model.User;
import service.UserService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Welcome to the Mood Tracker App!");
            System.out.println("1. Registration");
            System.out.println("2. Login with email: ");
            System.out.println("3. Login with username: ");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            User currUser = null;
            if (choice == 1) {
                System.out.println("Enter username: ");
                String username = scanner.nextLine();

                System.out.println("Enter email: ");
                String email = scanner.nextLine();

                System.out.println("Enter password: ");
                String password = scanner.nextLine();

                userService.registerUser(username, email, password);
            }

            if (choice == 2) {
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Password: ");
                String password = scanner.nextLine();

                currUser = userService.loginUserWithEmail(email, password);
                if(currUser == null){
                    continue;
                }

                System.out.println("1. Add mood");
                System.out.println("2. View moods");
                System.out.println("3. Edit mood");
                System.out.println("4. Exit");
                choice = scanner.nextInt();
                scanner.nextLine();
                while (choice != 4) {
                    if (choice == 1) {
                        System.out.println("Enter mood name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter mood notes: ");
                        String notes = scanner.nextLine();
                        System.out.println("Enter mood category (HAPPY, SAD, ANGRY, EXCITED, ANXIOUS): ");
                        String categoryStr = scanner.nextLine();
                        MoodCategory category;
                        try {
                            category = MoodCategory.valueOf(categoryStr.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid mood category. Please try again.");
                            continue;
                        }
                        userService.addMood(currUser, name, notes, category);
                        System.out.println("Mood added successfully.");
                    }
                    if (choice == 2) {
                        System.out.println("Your moods:");
                        for (Mood m : userService.getMoodByUserId(currUser.getId())) {
                            System.out.println("ID: " + m.getId() + ", Name: " + m.getName() + ", Category: " + m.getMoodCategory() + ", Notes: " + m.getNote() + ", Date: " + m.getDateTime());
                        }
                    }
                    if (choice == 3) {
                        System.out.println("Enter the id of the mood you want to edit:");
                        int moodId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter new notes: ");
                        String newNotes = scanner.nextLine();
                        userService.editMood(moodId, newNotes);
                        System.out.println("Mood updated successfully.");
                    }
                }
            }

            if (choice == 3) {
                System.out.println("Username: ");
                String username = scanner.nextLine();
                System.out.println("Password: ");
                String password = scanner.nextLine();

                currUser = userService.loginUserWithEmail(username, password);
                if(currUser == null){
                    return;
                }

                while (choice != 4) {
                System.out.println("1. Add mood");
                System.out.println("2. View moods");
                System.out.println("3. Edit mood");
                System.out.println("4. Exit");
                choice = scanner.nextInt();
                scanner.nextLine();
                    if (choice == 1) {
                        System.out.println("Enter mood name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter mood notes: ");
                        String notes = scanner.nextLine();
                        System.out.println("Enter mood category (HAPPY, SAD, ANGRY, EXCITED, ANXIOUS): ");
                        String categoryStr = scanner.nextLine();
                        MoodCategory category;
                        try {
                            category = MoodCategory.valueOf(categoryStr.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid mood category. Please try again.");
                            continue;
                        }
                        userService.addMood(currUser, name, notes, category);
                        System.out.println("Mood added successfully.");
                    }
                    if (choice == 2) {
                        System.out.println("Your moods:");
                        for (Mood m : userService.getMoodByUserId(currUser.getId())) {
                            System.out.println("ID: " + m.getId() + ", Name: " + m.getName() + ", Category: " + m.getMoodCategory() + ", Notes: " + m.getNote() + ", Date: " + m.getDateTime());
                        }
                    }
                    if (choice == 3) {
                        System.out.println("Enter the id of the mood you want to edit:");
                        int moodId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter new notes: ");
                        String newNotes = scanner.nextLine();
                        userService.editMood(moodId, newNotes);
                        System.out.println("Mood updated successfully.");
                    }
                }
            }

            if (choice == 4) {
                System.out.println("Exiting the application. Goodbye!");
                return;
            }

        }
    }
}



























