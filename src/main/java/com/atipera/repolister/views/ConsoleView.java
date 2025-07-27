package com.atipera.repolister.views;
// ConsoleView.java (The "View" - responsible for I/O)
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleView {

    private final ConsoleViewModel viewModel; // Injected ViewModel
    private final Scanner scanner = new Scanner(System.in);

    // Spring will inject the ViewModel
    public ConsoleView(ConsoleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void displayWelcome() {
        System.out.println("--- Welcome to Console MVVM App ---");
        System.out.println("1. List all users");
        System.out.println("2. Add new user");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayUsers(List<String> userDisplayStrings) {
        if (userDisplayStrings.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\n--- Current Users ---");
            userDisplayStrings.forEach(System.out::println);
        }
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void startLoop() {
        while (true) {
            displayWelcome();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewModel.loadAndDisplayUsers();
                    break;
                case "2":
                    String name = getUserInput("Enter user name: ");
                    int age;
                    try {
                        age = Integer.parseInt(getUserInput("Enter user age: "));
                        viewModel.addNewUser(name, age);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid age. Please enter a number.");
                    }
                    break;
                case "3":
                    displayMessage("Exiting application. Goodbye!");
                    return;
                default:
                    displayMessage("Invalid choice. Please try again.");
            }
            System.out.println("\n"); // Newline for better readability
        }
    }
}