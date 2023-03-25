package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for home menu option
public class HomeMenuButton extends Button {

    public HomeMenuButton(VisualApp editor, JComponent parent) {
        super(editor, parent);

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Home");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new HomeButtonListener());
    }

    private class HomeButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.callHomeScreen();
        }
    }
}
