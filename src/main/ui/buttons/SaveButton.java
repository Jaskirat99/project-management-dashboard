package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for save option
public class SaveButton extends Button {

    public SaveButton(VisualApp editor, JComponent parent) {
        super(editor, parent);

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Save Data");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveButtonListener());
    }

    private class SaveButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.saveScreen();
        }
    }
}
