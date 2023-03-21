package ui;

import model.Project;
import persistence.ReadJson;
import persistence.WriteJson;
import ui.buttons.Button;
import ui.buttons.LoadButton;
import ui.buttons.SaveButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    List<Project> projects;
    //List<Button> buttons;

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
    }

    // MODIFIES: this
    // EFFECTS: Draws the top menu bar menu
    private void initializeTopBar() {
        JMenu menu = new JMenu("File Options");
        JMenuBar mb = new JMenuBar();

        Button saveButton = new SaveButton(this,menu);
        //buttons.add(saveButton);
        Button deleteButton = new LoadButton(this,menu);
        //buttons.add(deleteButton);

        mb.add(menu);
        setJMenuBar(mb);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }
    
    //
    private void initializeMainScreen() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("data/mainScreen.png"));
        } catch (IOException e) {
            System.out.println("File not found");
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        add(picLabel);
        setVisible(true);
    }

    // EFFECTS: Public accessor method for save button
    public void callSaveScreen() {
        saveScreen();
    }

    // MODIFIES: this
    // EFFECTS: Draws the screen that displays the saving options
    private void saveScreen() {
        JPanel pm = new JPanel();

        // create a label
        JLabel l = new JLabel("this is the popup menu");

        // add the label to the popup
        pm.add(l);

        // add the popup to the frame
        this.add(pm);
        pm.setVisible(true);
    }


}

