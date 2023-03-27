package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for view entries option
public class ViewEntriesButton extends Button {
    public ViewEntriesButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("View Transactions");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewEntriesButtonListener());
    }

    private class ViewEntriesButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.viewTransactionsMenu();
        }
    }
}
