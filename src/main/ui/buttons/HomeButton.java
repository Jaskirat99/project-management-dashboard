package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeButton extends Button {

    public HomeButton(VisualApp editor, JComponent parent) {
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

        // EFFECTS: Calls homeScreen method
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.callHomeScreen();
        }
    }
}
