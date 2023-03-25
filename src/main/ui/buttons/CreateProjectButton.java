package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectButton extends Button {

    public CreateProjectButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Create A Project");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new CreateProjectButtonListener());
    }

    private class CreateProjectButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.createProject();
        }
    }
}
