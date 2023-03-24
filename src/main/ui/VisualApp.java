package ui;

import model.Project;
import persistence.ReadJson;
import persistence.WriteJson;
import ui.buttons.ConfirmSaveButton;
import ui.buttons.HomeButton;
import ui.buttons.LoadButton;
import ui.buttons.SaveButton;
import ui.screens.Image;

import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        JMenu testMenu = new JMenu("test Options");

        new HomeButton(this,mb);
        new SaveButton(this,fileMenu);
        new LoadButton(this,fileMenu);

        mb.add(fileMenu);
        mb.add(testMenu);
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




    // EFFECTS: Public accessor method for save button
    public void callSaveScreen() {
        saveScreen();
    }

    // EFFECTS: Public accessor method for home button
    public void callHomeScreen() {
        initializeMainScreen();
    }

    // EFFECTS: Public accessor method for home button
    public void callSaveData() {
        saveState();
    }

    // INSPIRED BY UBC CPSC 210 JSON SERIALIZATION DEMO
    // EFFECTS: Attempts to save the current state of application, catches exception
    private void saveState() {

        projects.add(new Project("123"));
        projects.add(new Project("456"));

        if (projects.size() != 0) {
            try {
                jsonWriter.open();
                jsonWriter.write(projects);
                jsonWriter.close();

                for (Project project : projects) {
                    System.out.println("Saved " + project.getAddress() + " to " + JSON_DESTINATION);
                    for (int i = 0; i < projects.size(); i++) {
                        clearScreen();
                        String str = "Saved " + projects.get(i).getAddress() + " With "
                                + projects.get(i).getNumberOfTransactions() + " Transactions";
                        displayProjects(str, 24, "TOP");
                        setVisible(true);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_DESTINATION);
            }
        } else {
            clearScreen();
            JLabel saveText = labelMaker("You Have No Projects To Save! Please Go Home And Create Some",
                    28, "CENTER");
            add(saveText);
            setVisible(true);
        }
    }


    // EFFECTS: Displays all existing projects
    private void displayProjects(String text, int fontSize, String alignment) {
        List<JLabel> labels = createLabelArray(text,fontSize,alignment);
        for (int i = 0; i < labels.size(); i++) {
            this.add(labels.get(i));
        }
    }

    private List<JLabel> createLabelArray(String text, int fontSize, String alignment) {
        List<JLabel> labels = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            labels.add(labelMaker(text,fontSize,alignment));
        }
        return labels;
    }


    // MODIFIES: this
    // EFFECTS: Draws the screen that displays the saving options
    private void saveScreen() {
        clearScreen();
        JLabel saveText = labelMaker("Please Confirm You Would Like To Save All Data", 28,
                "TOP");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(saveText);
        new ConfirmSaveButton(this, defaultPanel);
        add(defaultPanel);
        setVisible(true);

    }


    private void clearScreen() {
        this.getContentPane().removeAll();
        defaultPanel.removeAll();
        this.repaint();
        getContentPane().setBackground(Color.BLACK);
        defaultPanel.setBackground(Color.BLACK);

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


}

