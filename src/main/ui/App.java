package ui;

import model.*;
import java.util.*;
import static model.Utilities.*;

// Main UI class, runs the application through a central loop managed in runApp();
public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

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
    }



    // EFFECTS: displays the menu of options and prompts user to select an option
    private void printMenu() {

        String[] options = {ANSI_CYAN + "***Welcome To Your Project Dashboard!***" + ANSI_RESET,
                            "Please Select From The Following Options:",
                            ANSI_CYAN + "1 " + ANSI_RESET + " - Add New Project",
                            ANSI_CYAN + "2 " + ANSI_RESET + " - View Transactions",
                            ANSI_CYAN + "3 " + ANSI_RESET + " - Add New Transaction",
                            ANSI_CYAN + "4 " + ANSI_RESET + " - View Tax Information",
                            ANSI_CYAN + "5 " + ANSI_RESET + " - See Project Summaries",
                            ANSI_CYAN + "6 " + ANSI_RESET + " - Set Targets",
                            ANSI_CYAN + "7 " + ANSI_RESET + " - Amend A Transaction",
                            ANSI_CYAN + "X " + ANSI_RESET + " - Help",
                            ANSI_CYAN + "Y " + ANSI_RESET + "- Quit Application"};

        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("\nEnter Input Here:");
    }

    // EFFECTS: Calls appropriate methods based on input or calls method to handle invalid input
    private void runCommand(String command) {
        switch (command.toLowerCase()) {
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
            case "x": displayHelpMenu();
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
            System.out.println("Perfect! Please select a transaction from the below list ");
            printTransactions(choice);
            int transactionChoice = (Integer.parseInt(input.next())) - 1;
            System.out.println("What aspect of the transaction would you like to change?\n1.Payee\n2.Amount"
                    + "\n3.Payment Type\n4.Tax Included\n5.Tax");
            int transactionAspect = Integer.parseInt(input.next());
            displayAmendOptions(transactionAspect, transactionChoice, choice);
            loopControl = displayEndScreen("Amend Another Transaction");

        }

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // EFFECTS: Displays the different options for which aspect of a transaction to amend.
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

    // EFFECTS: Informs user of invalid inputs and provides options to proceed
    private String handleInvalidInput() {
        System.out.println("Sorry! That input is not valid!");
        System.out.println("Enter: ");
        System.out.println("1 - To Quit Program ");
        System.out.println("2 - To View Main Menu ");
        System.out.println("3 - To View Help Menu ");

        String s = input.next();
        String retry = "Try again";
        switch (s) {
            case "1": System.exit(1);
            case "2": runApp();
            break;
            case "3": displayHelpMenu();
            break;
            default: handleInvalidInput();
        }
        return retry;
    }

    // EFFECTS: Displays a menu that explains what the different options do.
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


    // EFFECTS: Displays a menu that shows overall summary information of a chosen project
    private void displayProjectSummary() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Which project would you like to see a summary for?");
            int choice = displayProjects();
            System.out.println("Perfect! Here are the details you requested");
            System.out.println("Project address: " + projects.get(choice).getAddress());
            System.out.println("Number of Transactions: " + projects.get(choice).getAllTransactions().size());
            System.out.println("Project Target: " + formatNumbers(projects.get(choice).getTargetSale()));
            System.out.println("Total Project Cost So Far: " + formatNumbers(projects.get(choice).getTotalCost()));
            System.out.println("Project Cash Cost So Far: "
                    + formatNumbers(projects.get(choice).calculateCostBreakdown(1)));
            System.out.println("Project Cheque Cost So Far: "
                    + formatNumbers(projects.get(choice).calculateCostBreakdown(2)));
            System.out.println("Project Visa Cost So Far: "
                    + formatNumbers(projects.get(choice).calculateCostBreakdown(3)));
            displayTargetBudgetInformation(choice);
            loopControl = displayEndScreen("View Another Project Summary");
        }
    }

    //EFFECTS: Displays the information of whether a project is over or under budget if a budget is set
    private void displayTargetBudgetInformation(int choice) {
        if (projects.get(choice).getTargetSale() > 0) {
            if (projects.get(choice).getTotalCost() > projects.get(choice).getTargetSale()) {
                System.out.println("You are over budget by "
                        + formatNumbers(projects.get(choice).getTotalCost() - projects.get(choice).getTargetSale())
                        + "!!!!");
            } else {
                System.out.println("You are on track!! Your budget still allows for another "
                        + formatNumbers(projects.get(choice).getTargetSale()
                        - projects.get(choice).getTotalCost()));
            }
        } else {
            System.out.println("You must set a target before you can view your budget information!!");
        }
    }

    // EFFECTS: Sets a target price for expected sale price (budget of project)
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

    //EFFECTS: Displays information on tax breakdown
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


    // EFFECTS: Prompts user to create new transaction
    private void addNewTransaction() {
        int loopControl = 0;
        while (loopControl != 1) {
            System.out.println("Alright! Let's get started! \nWho is the payee of this transaction?: ");
            String payee = input.next();
            System.out.println("What is the amount of the transaction?");
            double amount = lookForAmountErrors();

            System.out.println("What is the purchase type (cheque, cash or visa)?");
            String purchaseType = input.next().toLowerCase();
            checkInvalidPurchaseType(purchaseType);
            System.out.println("The transaction will be dated to today's date");
            System.out.println("Is tax included in the transaction amount (Enter 'yes' or 'no'?");
            String temp = input.next().toLowerCase();
            Boolean taxIncluded = formatTaxIncluded(temp);
            System.out.println("What type of tax is this transaction subject to (GST/PST/BOTH)?");
            String taxType = input.next().toLowerCase();
            checkInvalidTaxType(taxType);
            System.out.println("Lastly, what project is this transaction for?");
            int choice = displayProjects();

            projects.get(choice).createEntry(payee,purchaseType,taxIncluded,taxType,amount);
            loopControl = displayEndScreen("Create another transaction");
        }
    }

    private void checkInvalidPurchaseType(String purchaseType) {
        if ((Objects.equals("cash", purchaseType))
                || (Objects.equals("cheque", purchaseType))
                || (Objects.equals("visa", purchaseType))) {
            int temp;
        } else {
            handleInvalidInput();
        }
    }

    // EFFECTS: Checks if amount is a valid double
    private double lookForAmountErrors() {
        double amount = 0;
        try {
            amount = Double.parseDouble(input.next());
        } catch (Exception e) {
            handleInvalidInput();
        }
        return amount;
    }


    private void checkInvalidTaxType(String taxType) {
        if ((Objects.equals("gst", taxType))
                || (Objects.equals("pst", taxType))
                || (Objects.equals("both", taxType))) {
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
        if (projects.get(project).getAllTransactions().size() > 0) {
            for (int i = 0; i < projects.get(project).formatTransactions().size(); i++) {
                System.out.println(counter + ". " + projects.get(project).formatTransactions().get(i));
                counter++;
            }
        } else {
            System.out.println("There are no transactions to display!");
            displayEndScreen("Create a transaction");
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
            runApp();
        } else if (option == 3) {
            System.exit(1);
        } else {
            displayEndScreen(handleInvalidInput());
        }
        return 1;

    }
}