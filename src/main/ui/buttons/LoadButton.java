package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for load button option
public class LoadButton extends Button {

    public LoadButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Load Data");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new DeleteButtonListener());
    }

    private class DeleteButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.loadScreen();
        }
    }
}
