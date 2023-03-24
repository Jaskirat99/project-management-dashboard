package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmLoadButton extends Button {
    private static final Color GOLD = new Color(174,154,127);

    public ConfirmLoadButton(VisualApp editor, JComponent parent) {
        super(editor, parent);

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Yes, Load Data!");
        button.setForeground(GOLD);
        button.setBackground(Color.BLACK);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ConfirmLoadButtonListener());
    }

    private class ConfirmLoadButtonListener implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.loadData();
        }
    }
}
