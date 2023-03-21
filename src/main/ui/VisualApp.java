package ui;

import model.Project;
import persistence.ReadJson;
import persistence.WriteJson;
import ui.buttons.HomeButton;
import ui.buttons.LoadButton;
import ui.buttons.SaveButton;
import ui.screens.Image;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a GUI app
public class VisualApp extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private static final String JSON_DESTINATION = "./data/project.json";
    File cash;
    File visa;
    File cheque;
    private WriteJson jsonWriter;
    private ReadJson jsonReader;
    private Scanner input;
    private JPanel mainPicturePanel = new JPanel();
    private JPanel defaultPicturePanel = new JPanel();
    List<Project> projects;
    private Image activeImage;

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
        projects = new ArrayList<>();
        //buttons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the app will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.BLACK);
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
        defaultPicturePanel.setVisible(false);
        mainPicturePanel.setVisible(true);
        mainPicturePanel.add(new Image("data/mainScreen.png", mainPicturePanel));
        add(mainPicturePanel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the default page image
    private void initializeDefaultScreen() {
        mainPicturePanel.setVisible(false);
        defaultPicturePanel.setVisible(true);
        defaultPicturePanel.add(new Image("data/defaultScreen.png", defaultPicturePanel));
        add(defaultPicturePanel);
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

    // MODIFIES: this
    // EFFECTS: Draws the screen that displays the saving options
    private void saveScreen() {
        clearScreen();
        initializeDefaultScreen();

    }

    private void clearScreen() {
        this.getContentPane().removeAll();
        this.repaint();
    }


}

