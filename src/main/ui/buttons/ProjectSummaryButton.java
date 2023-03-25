package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for project summary option
public class ProjectSummaryButton extends Button {
    public ProjectSummaryButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("View Summaries");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ProjectSummaryButtonListener());
    }

    private class ProjectSummaryButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.viewSummary();
        }
    }
}
