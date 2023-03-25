package ui.buttons;

import ui.VisualApp;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for the ammend entry option
public class AmmendEntryButton extends Button {
    public AmmendEntryButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Ammend Transactions");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AmmendEntryButtonListener());
    }

    private class AmmendEntryButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute ammend option
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
