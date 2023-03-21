package ui.screens;

import ui.VisualApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends JPanel {
    private String destination;
    private JLabel label;
    private JPanel panel;

    public Image(String destination, JPanel panel) {
        this.destination = destination;
        this.panel = panel;
        BufferedImage pic = null;
        try {
            pic = ImageIO.read(new File(destination));
        } catch (IOException e) {
            System.out.println("File not found");
        }

        this.label = new JLabel(new ImageIcon(pic));
        panel.add(label);
        panel.addComponentListener(new ResizeListener());
    }

    // MODIFIES: this
    // EFFECTS: Listens for when frame is resized and updates home page size to match
    class ResizeListener extends ComponentAdapter {

        public void componentResized(ComponentEvent e) {
            ImageIcon icon = new ImageIcon(destination);
            java.awt.Image img = icon.getImage();
            java.awt.Image imgScale = img.getScaledInstance(panel.getWidth(), panel.getHeight(),
                    java.awt.Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            label.setIcon(scaledIcon);
            panel.add(label);
        }
    }
}
