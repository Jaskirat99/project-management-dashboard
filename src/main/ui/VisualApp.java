package ui;

import model.Project;
import persistence.ReadJson;
import persistence.WriteJson;
import ui.buttons.*;
import ui.buttons.Button;
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
        new ProjectTargetButton(this, projectMenu);
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
    // EFFECTS: Creates a new project
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

            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_DESTINATION);
            }
        } else {
            clearScreen();
            JLabel saveText = labelMaker("You Have No Projects To Save! Please Go Home And Create Some",
                    28, "CENTER");
            add(saveText);

        }
        new HomeButton(this,defaultPanel);
        add(defaultPanel);
        setVisible(true);
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
                    new HomeButton(this,defaultPanel);
                    add(defaultPanel);
                }
            } else {
                clearScreen();
                JLabel loadText = labelMaker("You Have No Projects To Load! Please Go Home And Create Some",
                        28, "CENTER");
                add(loadText);
                new HomeButton(this,defaultPanel);
                add(defaultPanel);
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
    private void displayProjects(String text, int fontSize, String alignment) {
        List<JLabel> labels = createLabelArray(text,fontSize,alignment);
        for (int i = 0; i < labels.size(); i++) {
            this.add(labels.get(i));
        }
    }

    // EFFECTS: Creates an array of labels with given parameters
    private List<JLabel> createLabelArray(String text, int fontSize, String alignment) {
        List<JLabel> labels = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            labels.add(labelMaker(text,fontSize,alignment));
        }
        return labels;
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

