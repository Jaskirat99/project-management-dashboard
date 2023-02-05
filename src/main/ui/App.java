package ui;

import model.*;
import java.util.*;
import static model.Utilities.*;


public class App {
    private Scanner input = new Scanner(System.in);
    List<Project> projects = new ArrayList<>();

    //EFFECTS: runs the application
    public App() {
        runApp();
    }

    // EFFECTS: Manages Main Loop
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
                            "7 - Amend A Transaction",
                            "X - Help",
                            "Y - Quit Application"};

        int numOptions = options.length;

        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("\nEnter Input Here:");
    }

    // REQUIRES: None empty input
    // EFFECTS: Calls Appropriate methods based on input
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
            break;
            case "7": amendTransaction();
            break;
            case "x":
            case "X":
                displayHelpMenu();
                break;
            default: runCommand(handleInvalidInput());
        }

    }

    private void amendTransaction() {
    }

    // EFFECTS: Informs user of invalid inputs and starts over
    private String handleInvalidInput() {
        System.out.println("Sorry! That input is not valid!");
        System.out.println("Enter: ");
        System.out.println("1 - To Quit Program ");
        System.out.println("2 - To Try Again With Valid Input ");
        System.out.println("3 - To View Help Menu ");

        String s = input.next();
        String retry = "Try again";
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
        System.out.println("Alright! Let's get started! \nWho is the payee of this transaction?: ");
        String payee = input.next();
        System.out.println("What is the amount of the transaction?");
        try {
            String amount = formatNumbers(Double.parseDouble(input.next()));
        } catch (Exception e) {
            handleInvalidInput();
        }
        System.out.println("What is the purchase type (cheque, cash or visa)?");
        String purchaseType = formatNumbers(Double.parseDouble(input.next()));
        System.out.println("When was the transaction made (Enter 'today' or a 8 digit number formed as DDMMYYYY)?");
        String date = input.next();


    }

    // EFFECTS: Displays transactions for selected project or prompts you to create a project
    private void displayTransactions() {
        System.out.println("Which project would you like to view transactions for? ");
        if (projects.size() == 0) {
            handleEmptyProjects();
        } else {
            for (int i = 0; i < projects.size(); i++) {
                System.out.println((i + 1) + ". " + projects.get(i).getAddress());
            }
        }
        int choice = Integer.parseInt(input.next()) - 1;
        System.out.println("Sounds good! Here are the transactions for " + projects.get(choice).getAddress());

        for (int i = 0; i < projects.get(choice).formatTransactions().size(); i++) {
            System.out.println(projects.get(choice).formatTransactions().get(i));
        }

    }

    // EFFECTS: Prompts user to increase create new project
    private void handleEmptyProjects() {
        System.out.println("Sorry! You don't have any projects to display! \nWould you like to create some?"
                + " Enter '1' for yes or 2 to exit");
        int choice = Integer.parseInt(input.next());
        if (choice == 1) {
            createNewProject();
        } else if (choice == 2) {
            System.exit(1);
        } else {
            handleInvalidInput();
        }
    }

    //EFFECTS: Creates new projects as long as user would like
    private void createNewProject() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("What is the address of the project you will like to create? ");
            String address = input.next();

            Project p1 = new Project(address);
            projects.add(p1);

            System.out.println("Okay! I've created a new project called " + p1.getAddress());
            loopControl = displayEndScreen("Create another project");
        }
    }

    private int displayEndScreen(String option1) {
        System.out.println("Would you like to \n 1. " + option1 + " \n 2. View the main menu \n 3. Quit");
        int option = Integer.parseInt(input.next());
        if (option == 1) {
            return 0;
        } else if (option == 2) {
            return 1;
        } else if (option == 3) {
            System.exit(1);
        } else {
            displayEndScreen(handleInvalidInput());
        }
        return 1;

    }
}
