package ui;

import model.*;
import java.util.Scanner;

public class App {
    private Scanner input = new Scanner(System.in);

    //EFFECTS: runs the application
    public App() {
        runApp();
    }

    private void runApp() {
        String userInput = "temporary";
        boolean keepGoing = true;

        initializeApp();

        while (keepGoing) {
            printMenu();
            userInput = input.next();

            if ((userInput.equals("y")) || (userInput.equals("Y"))) {
                keepGoing = false;
            } else {
                runCommand(userInput);
            }
        }

        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the app to defaults
    private void initializeApp() {

    }

    // EFFECTS: displays the menu of options
    private void printMenu() {

        String[] options = {"Welcome To Your Project Dashboard!",
                            "Please Select From The Following Options:",
                            "1 - Add New Project",
                            "2 - View Transactions",
                            "3 - Add New Transaction",
                            "4 - View Tax Information",
                            "5 - See Project Summaries",
                            "6 - Set Targets",
                            "X - Help",
                            "Y - Quit Application"};

        int numOptions = options.length;

        for (int i = 0; i < numOptions; i++) {
            System.out.println(options[i]);
        }
        System.out.print("\nEnter Input Here:");
    }

    private void runCommand(String command) {
        switch (command) {
            case "1": createNewProject();
            break;
            case "2": displayTransactions();
            break;
            case "3": addNewTransaction();
            break;
            case "4": displayTaxInfo();
            break;
            case "5": displayProjectSummary();
            break;
            case "6": setTargets();
            case "x": displayHelpMenu();
            break;
            case "X": displayHelpMenu();
            break;
            default: runCommand(handleInvalidInput());
        }

    }

    // EFFECTS: Informs user of invalid inputs and starts over
    private String handleInvalidInput() {
        System.out.println("Sorry! That input is not valid!");
        System.out.println("Enter: ");
        System.out.println("1 - To Quit Program ");
        System.out.println("2 - To Try Again With Valid Input ");
        System.out.println("3 - To View Help Menu ");

        String s = input.next();
        String retry = "temp";
        switch (s) {
            case "1": System.exit(1);
            case "2": retry = tryInputAgain();
            break;
            case "3": displayHelpMenu();
            break;
            default: handleInvalidInput();
        }
        return retry;
    }

    private String tryInputAgain() {
        System.out.print("Please enter a valid input: ");
        String retry = input.next();

        return retry;
    }

    private void displayHelpMenu() {
    }

    private void displayProjectSummary() {
    }

    private void setTargets() {
    }

    private void displayTaxInfo() {
    }

    private void addNewTransaction() {
    }

    private void displayTransactions() {

    }

    private void createNewProject() {
    }
}
