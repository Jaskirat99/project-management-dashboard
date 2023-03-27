package ui.buttons;


import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for view entries option
public class ChequeFilterButton extends Button {
    private String filter;
    private int choice;

    public ChequeFilterButton(VisualApp editor, JComponent parent, String filter, int choice) {
        super(editor, parent);
        this.filter = filter;
        this.choice = choice;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Filter By Cheque");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ChequeFilterButtonListener());
    }

    private class ChequeFilterButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.filteredViewTransactions(filter, choice);
        }
    }
}
