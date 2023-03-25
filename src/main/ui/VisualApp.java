package ui;

import model.Project;
import persistence.ReadJson;
import persistence.WriteJson;
import ui.buttons.*;
import ui.screens.Image;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static model.Utilities.*;

// Represents a GUI app
public class VisualApp extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private static final Color GOLD = new Color(174,154,127);
    private static final String JSON_DESTINATION = "./data/project.json";
    File cash;
    File visa;
    File cheque;
    private WriteJson jsonWriter;
    private ReadJson jsonReader;
    private Scanner input;
    private JPanel mainPicturePanel = new JPanel();
    private JPanel defaultPanel = new JPanel();
    private List<JRadioButton> projectButtonList = new ArrayList();
    private JTextField enterAddress = new JTextField("Replace This With String");
    private JTextField enterTarget = new JTextField("Replace This With Number");


    List<Project> projects = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: Constructs a new GUI
    public VisualApp() {
        super("Project Management Dashboard");
        initializeFields();
        initializeTopBar();
        initializeGraphics();
        initializeMainScreen();

    }

    // MODIFIES: this
    // EFFECTS: initializes spelling file locations, json writer/reader destinations, scanner and projects arraylist
    private void initializeFields() {
        cash = new File("data/CashSpellings");
        visa = new File("data/VisaSpellings");
        cheque = new File("data/ChequeSpellings");
        jsonWriter = new WriteJson(JSON_DESTINATION);
        jsonReader  = new ReadJson(JSON_DESTINATION);
        input = new Scanner(System.in).useDelimiter("\\n");
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the app will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //add(layeredPane, BorderLayout.CENTER);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Draws the top menu bar menu
    private void initializeTopBar() {
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File Options");
        JMenu projectMenu = new JMenu("Project Options");
        JMenu entryMenu = new JMenu("Entry Options");

        new HomeMenuButton(this,mb);
        new SaveButton(this,fileMenu);
        new LoadButton(this,fileMenu);
        new CreateProjectButton(this, projectMenu);
        new ProjectSummaryButton(this, projectMenu);
        new CreateEntryButton(this, entryMenu);
        new ViewEntriesButton(this, entryMenu);
        new AmmendEntryButton(this, entryMenu);

        mb.add(fileMenu);
        mb.add(projectMenu);
        mb.add(entryMenu);
        setJMenuBar(mb);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the main "home page" image
    private void initializeMainScreen() {
        clearScreen();
        setLayout(new BorderLayout());
        mainPicturePanel.setVisible(true);
        mainPicturePanel.add(new Image("data/mainScreen.png", mainPicturePanel));
        add(mainPicturePanel);
        setVisible(true);
    }

    // MODIFIES: projects
    // EFFECTS: Creates a new project walk through
    public void createProject() {
        clearScreen();
        JLabel text = labelMaker("Please Fill Out The Following Blanks To Create A New Project",
                28, "TOP");
        JLabel addressText = labelMaker("Enter Address Below:",
                28, "TOP");
        JLabel targetText = labelMaker("Enter Estimated Sale Price Below:",
                28, "TOP");
        formatTargetAndAddress();
        new SubmitProjectButton(this,defaultPanel);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(text);
        add(addressText);
        add(enterAddress);
        add(targetText);
        add(enterTarget);
        add(defaultPanel);
        setVisible(true);
    }

    // EFFECTS: Helper method for createProject(). Formats enterAddress & enterTarget
    private void formatTargetAndAddress() {
        enterAddress.setForeground(GOLD);
        enterAddress.setBackground(Color.BLACK);
        enterAddress.setBorder(BorderFactory.createLineBorder(GOLD));
        enterAddress.setHorizontalAlignment(JTextField.CENTER);
        enterAddress.setFont(new Font("Serif", Font.PLAIN, 18));
        enterTarget.setForeground(GOLD);
        enterTarget.setBackground(Color.BLACK);
        enterTarget.setBorder(BorderFactory.createLineBorder(GOLD));
        enterTarget.setHorizontalAlignment(JTextField.CENTER);
        enterTarget.setFont(new Font("Serif", Font.PLAIN, 18));
    }

    // MODIFIES: projects
    // EFFECTS Helper method for createProject(). Constructs new project when submitProject Button clicked
    public void submitProject() {
        clearScreen();
        Project p1 = new Project(enterAddress.getText());
        p1.setTargetSale(Double.parseDouble(enterTarget.getText()));
        projects.add(p1);
        String str = "Added " + p1.getAddress() + " With A Target Of "
                + formatNumbers(p1.getTargetSale());
        add(labelMaker(str,24, "TOP"));
        new HomeButton(this,defaultPanel);
        add(defaultPanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Displays summary for chosen project
    public void viewSummary() {
        clearScreen();
        JLabel select = labelMaker("Please Select Which Project You Would Like To View A Summary For",
                28, "TOP");
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(select);
        displayProjects();
        new SelectButton(this,defaultPanel);
        add(defaultPanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Determines which project was selected by user and calls helper to display that projects information
    public void selectPressed() {
        int choice = -1;
        for (int i = 0; i < projectButtonList.size(); i++) {
            if (projectButtonList.get(i).isSelected()) {
                choice = i;
            }
        }
        clearScreen();
        displayProjectsStats(choice);
        new HomeButton(this,defaultPanel);
        add(defaultPanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Displays summary for given project
    private void displayProjectsStats(int choice) {
        JLabel title = labelMaker("Below Is The Summary For " + projects.get(choice).getAddress(),
                28, "TOP");
        JLabel numTransactions = labelMaker("Number Of Transactions: "
                        + projects.get(choice).getNumberOfTransactions(), 24, "TOP");
        JLabel target = labelMaker("Target: "
                + formatNumbers(projects.get(choice).getTargetSale()), 24, "TOP");
        JLabel totalCost = labelMaker("Total Cost: "
                + formatNumbers(projects.get(choice).getTotalCost()), 24, "TOP");
        JLabel cashCost = labelMaker("Cash Cost: "
                + formatNumbers(projects.get(choice).calculateCostBreakdown(1)), 24, "TOP");
        JLabel chequeCost = labelMaker("Cheque Cost: "
                + formatNumbers(projects.get(choice).calculateCostBreakdown(2)), 24, "TOP");
        JLabel visaCost = labelMaker("Visa Cost: "
                + formatNumbers(projects.get(choice).calculateCostBreakdown(3)), 24, "TOP");
        add(title);
        add(numTransactions);
        add(target);
        add(totalCost);
        add(cashCost);
        add(chequeCost);
        add(visaCost);
        setVisible(true);
        displayTargetBudgetInfo(choice);
    }

    // MODIFIES: this
    // EFFECTS: Informs user whether they are over or under budget
    private void displayTargetBudgetInfo(int choice) {
        JLabel targetInfo;
        if (projects.get(choice).getTargetSale() > 0) {
            if (projects.get(choice).getTotalCost() > projects.get(choice).getTargetSale()) {
                targetInfo = labelMaker("You are over budget by "
                        + formatNumbers(projects.get(choice).getTotalCost() - projects.get(choice).getTargetSale())
                        + " !!!!", 28, "TOP");
            } else {
                targetInfo = labelMaker("You are on track!! Your budget still allows for another "
                        + formatNumbers(projects.get(choice).getTargetSale()) + " !!!", 28, "TOP");
            }
        } else {
            targetInfo = labelMaker("You must set a target before you can view your budget information!!",
                    28, "TOP");
        }
        add(targetInfo);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Draws the screen that displays the saving options
    public void saveScreen() {
        clearScreen();
        JLabel saveText = labelMaker("Please Confirm You Would Like To Save All Data", 28,
                "TOP");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(saveText);
        new ConfirmSaveButton(this, defaultPanel);
        add(defaultPanel);
        setVisible(true);

    }

    // INSPIRED BY UBC CPSC 210 JSON SERIALIZATION DEMO
    // MODIFIES: project.json
    // EFFECTS: Attempts to save the current state of application, catches exception
    public void saveState() {
        if (projects.size() != 0) {
            try {
                jsonWriter.open();
                jsonWriter.write(projects);
                jsonWriter.close();

                clearScreen();
                for (int i = 0; i < projects.size(); i++) {
                    String str = "Saved " + projects.get(i).getAddress() + " With "
                            + projects.get(i).getNumberOfTransactions() + " Transactions";
                    add(labelMaker(str,24, "TOP"));
                }
                new HomeButton(this,defaultPanel);
                add(defaultPanel);
                setVisible(true);

            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_DESTINATION);
            }
        } else {
            handleEmptyProjects("You Have No Projects To Save! Please Go Home And Create Some");
        }

    }

    // MODIFIES: this
    // EFFECTS: Draws the screen that displays the loading options
    public void loadScreen() {
        clearScreen();
        JLabel loadText = labelMaker("Please Confirm You Would Like To Load All Data", 28,
                "TOP");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(loadText);
        new ConfirmLoadButton(this, defaultPanel);
        add(defaultPanel);
        setVisible(true);

    }

    // INSPIRED BY UBC CPSC 210 JSON SERIALIZATION DEMO
    // EFFECTS: Attempts to load from given file
    public void loadData() {
        try {
            projects = jsonReader.read();

            if (projects.size() != 0) {
                clearScreen();
                for (int i = 0; i < projects.size(); i++) {
                    String str = "Loaded " + projects.get(i).getAddress() + " With "
                            + projects.get(i).getNumberOfTransactions() + " Transactions";
                    add(labelMaker(str, 24, "TOP"));
                }
                new HomeButton(this,defaultPanel);
                add(defaultPanel);
            } else {
                handleEmptyProjects("You Have No Projects To Load! Please Go Home And Create Some");
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_DESTINATION);
        }
        setVisible(true);


    }

    // REQUIRES: verticalAlignment ==  "TOP"  || verticalAlignment == "CENTER" || BOTTOM
    // EFFECTS: Returns a label with given parameters. Uses standardized colors
    private JLabel labelMaker(String text, int fontSize, String verticalAlignment) {
        JLabel created = new JLabel(text,SwingConstants.CENTER);
        created.setFont(new Font("Serif", Font.PLAIN, fontSize));
        created.setForeground(GOLD);
        if (verticalAlignment == "TOP") {
            created.setVerticalAlignment(JLabel.TOP);
        } else if (verticalAlignment == "CENTER") {
            created.setVerticalAlignment(JLabel.CENTER);
        } else if (verticalAlignment == "BOTTOM") {
            created.setVerticalAlignment(JLabel.BOTTOM);
        }
        created.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        return created;
    }

    // MODIFIES: this
    // EFFECTS: Displays all existing projects
    private void displayProjects() {
        if (projects.size() != 0) {
            ButtonGroup g1 = new ButtonGroup();
            JPanel radioPanel = new JPanel();
            for (int i = 0; i < projects.size(); i++) {
                JRadioButton j1 = new JRadioButton();
                j1.setText(projects.get(i).getAddress());
                j1.setForeground(GOLD);
                j1.setFont(new Font("Serif", Font.PLAIN, 18));
                j1.setBackground(Color.BLACK);
                g1.add(j1);
                radioPanel.add(j1);
                projectButtonList.add(j1);
            }
            radioPanel.setBackground(Color.black);
            add(radioPanel);
            setVisible(true);
        } else {
            handleEmptyProjects("You Have No Projects To Display! Please Go Home And Create Some!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Informs user that they have no projects to display
    private void handleEmptyProjects(String input) {
        clearScreen();
        JLabel error = labelMaker(input, 28, "TOP");
        defaultPanel.add(error);
        new HomeButton(this,defaultPanel);
        add(defaultPanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Clears screen of all components and sets background to be plain black
    private void clearScreen() {
        this.getContentPane().removeAll();
        defaultPanel.removeAll();
        this.repaint();
        getContentPane().setBackground(Color.BLACK);
        defaultPanel.setBackground(Color.BLACK);

    }

    // EFFECTS: Public accessor method for home button
    public void callHomeScreen() {
        initializeMainScreen();
    }

}

