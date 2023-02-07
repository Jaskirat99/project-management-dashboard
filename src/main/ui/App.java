package ui;

import model.*;
import java.util.*;
import static model.Utilities.*;

public class App {
    private Scanner input = new Scanner(System.in).useDelimiter("\\n");
    List<Project> projects = new ArrayList<>();

    //EFFECTS: runs the application
    public App() {
        runApp();
    }

    // EFFECTS: Manages Main Loop, terminating app when "quit" input is given
    private void runApp() {
        String userInput;
        boolean keepGoing = true;

        while (keepGoing) {
            printMenu();
            userInput = input.next();

            if ((userInput.equalsIgnoreCase("y"))) {
                keepGoing = false;
            } else {
                runCommand(userInput);
            }
        }

        System.out.println("See you next time!");
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

    // EFFECTS: prompts user to pick a specific project -> entry -> field of entry to amend and fix
    private void amendTransaction() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Which project would you like to amend a transaction for?");
            int choice = displayProjects();
            System.out.println("Perfect! Please select a transaction from the below list "
                    + projects.get(choice).getAddress());
            printTransactions(choice);
            int transactionChoice = (Integer.parseInt(input.next())) - 1;
            System.out.println("What aspect of the transaction would you like to change?\n1.Payee\n2.Amount"
                    + "\n3.Payment Type\n4.Tax Included\n5.Tax Type\n6.Project");
            int transactionAspect = Integer.parseInt(input.next());
            displayAmendOptions(transactionAspect, transactionChoice, choice);
            loopControl = displayEndScreen("Amend Another Transaction");

        }

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void displayAmendOptions(int transactionAspect, int transactionChoice, int choice) {
        switch (transactionAspect) {
            case 1: System.out.println("What is the new payee?");
                projects.get(choice).getTransaction(transactionChoice).setPayee(input.next());
                System.out.println("Okay, I've set the payee to be "
                        + projects.get(choice).getTransaction(transactionChoice).getPayee());
                break;
            case 2: System.out.println("What is the new amount?");
                projects.get(choice).getTransaction(transactionChoice).setAmount(Integer.parseInt(input.next()));
                System.out.println("Okay, I've set the amount to be "
                        + projects.get(choice).getTransaction(transactionChoice).getAmount());
                break;
            case 3:System.out.println("What is the new Payment Type?");
                projects.get(choice).getTransaction(transactionChoice).setPaymentType(input.next());
                System.out.println("Okay, I've set the Payment Type to be "
                        + projects.get(choice).getTransaction(transactionChoice).getPaymentType());
                break;
            case 4:System.out.println("Was Tax included?");
                projects.get(choice).getTransaction(transactionChoice).setTaxPaid(Boolean.valueOf(input.next()));
                System.out.println("Okay, I've set the tax included state to be "
                        + projects.get(choice).getTransaction(transactionChoice).getTaxPaid());
                break;
            case 5:System.out.println("What is the new tax type (GST/PST/BOTH)?");
                projects.get(choice).getTransaction(transactionChoice).setTaxType(input.next());
                System.out.println("Okay, I've set the tax type to be "
                        + projects.get(choice).getTransaction(transactionChoice).getTaxType());
                break;
            default: handleInvalidInput();
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
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Welcome to the help menu!\nBelow is a description of what each menu option does:");
            System.out.println("1. Creates a project with your supplied address\n2. Lists all the transactions "
                    + "for a project of your choosing\n3. Allows you to create a new transaction for a project"
                    + "\n4. Displays tax information about a project\n5. Displays a summary for a project's progress"
                    + "\n6. Allows you to set a target for a project.\n7. Allows you to fix an error for any project.");
            loopControl = displayEndScreen("View Help Again");
        }
    }

    // TODO add cost breakdown
    private void displayProjectSummary() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Which project would you like to see a summary for?");
            int choice = displayProjects();
            System.out.println("Perfect! Here are the details you requested");
            System.out.println("Project address: " + projects.get(choice).getAddress());
            System.out.println("Number of Transactions: " + projects.get(choice).getAllTransactions().size());
            System.out.println("Project Target: " + projects.get(choice).getTargetSale());
            System.out.println("Project Cost So Far: " + projects.get(choice).getTotalCost());
            if (projects.get(choice).getTotalCost() > projects.get(choice).getTargetSale()) {
                System.out.println("You are over budget by "
                        + (projects.get(choice).getTotalCost() - projects.get(choice).getTargetSale())
                        + "!!!!");
            } else {
                System.out.println("You are on track!! Your budget still allows for another "
                        + (projects.get(choice).getTargetSale() - projects.get(choice).getTotalCost()));
            }
            loopControl = displayEndScreen("View Another Project Summary");
        }
    }

    private void setTargets() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Which project would you like to set a target for?");
            int choice = displayProjects();
            System.out.println("Perfect! How much are you expecting to sell project "
                    + projects.get(choice).getAddress() + " for? \nEnter a number below:");
            double amount = Double.parseDouble(input.next());
            projects.get(choice).setTargetSale(amount);
            System.out.println("Okay! I've set the target price for " + projects.get(choice).getAddress()
                    + " to be " + formatNumbers(projects.get(choice).getTargetSale()));
            loopControl = displayEndScreen("Set another target");
        }
    }

    private void displayTaxInfo() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Which project would you like to see tax information for?");
            int choice = displayProjects();
            System.out.println("Perfect! The tax details for " + projects.get(choice).getAddress() + " are:");
            System.out.println("Total Tax Paid: " + formatNumbers(projects.get(choice).calculateTotalTax()));
            System.out.println("Total GST Paid: " + formatNumbers(projects.get(choice).calculateCertainTax(1)));
            System.out.println("Total PST Paid: " + formatNumbers(projects.get(choice).calculateCertainTax(2)));
            loopControl = displayEndScreen("View more tax information");
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: Prompts user to create new transaction
    private void addNewTransaction() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Let's get started! \nWho is the payee of this transaction?: ");
            String payee = input.next();
            System.out.println("What is the amount of the transaction?");
            double amount = 0;
            try {
                amount = Double.parseDouble(input.next());
            } catch (Exception e) {
                handleInvalidInput();
            }
            System.out.println("What is the purchase type (cheque, cash or visa)?");
            String purchaseType = input.next();
            System.out.println("The transaction will be dated to today's date");
            System.out.println("Is tax included in the transaction amount (Enter 'yes' or 'no'?");
            String temp = input.next();
            Boolean taxIncluded = formatTaxIncluded(temp);
            System.out.println("What type of tax is this transaction subject to (GST/PST/BOTH)?");
            String taxType = input.next();
            checkInvalidTaxType(taxType);

            System.out.println("Lastly, what project is this transaction for?");
            int choice = displayProjects();
            projects.get(choice).createEntry(payee,purchaseType,taxIncluded,taxType,amount);
            loopControl = displayEndScreen("Create another transaction");
        }
    }

    private void checkInvalidTaxType(String taxType) {
        if ((Objects.equals("GST", taxType))
                || (Objects.equals("PST", taxType))
                || (Objects.equals("BOTH", taxType))) {
            int temp;
        } else {
            handleInvalidInput();
        }
    }

    private boolean formatTaxIncluded(String temp) {
        if (Objects.equals("yes", temp)) {
            return true;
        } else if (Objects.equals("no", temp)) {
            return false;
        } else {
            handleInvalidInput();
        }
        return false;
    }

    // EFFECTS: Displays transactions for selected project or prompts you to create a project
    private void displayTransactions() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Which project would you like to view transactions for? ");
            int choice = displayProjects();
            System.out.println("Sounds good! Here are the transactions for " + projects.get(choice).getAddress());
            printTransactions(choice);
            loopControl = displayEndScreen("View the transactions again");
        }

    }

    // EFFECTS: Print all transactions for an existing project
    private void printTransactions(int project) {
        int counter = 1;
        for (int i = 0; i < projects.get(project).formatTransactions().size(); i++) {
            System.out.println(counter + ". " + projects.get(project).formatTransactions().get(i));
            counter++;
        }
    }

    // EFFECTS: Displays all project addresses already created, if none exist then calls helper to handle case.
    // Returns which project user chose
    private int displayProjects() {
        if (projects.size() == 0) {
            handleEmptyProjects();
        } else {
            for (int i = 0; i < projects.size(); i++) {
                System.out.println((i + 1) + ". " + projects.get(i).getAddress());
            }
        }
        int choice = Integer.parseInt(input.next()) - 1;

        return choice;
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

    // REQUIRES: option1 is not empty
    // EFFECTS: Displays the end screen that is displayed after every operation is done. Option 1 text varies so is
    // supplied by the calling method.
    private int displayEndScreen(String option1) {
        System.out.println("----------------------------------------------------------------------------");
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
