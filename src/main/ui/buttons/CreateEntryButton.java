package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for the create entry option
public class CreateEntryButton extends Button {
    public CreateEntryButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Add Transaction");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new CreateEntryButtonListener());
    }

    private class CreateEntryButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.createEntryMenu();
        }
    }
}
