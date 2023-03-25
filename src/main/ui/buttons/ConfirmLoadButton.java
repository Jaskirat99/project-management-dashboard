package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for the confirmLoad option
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
        button.setPreferredSize(new Dimension(300,100));
        button.setBorder(BorderFactory.createLineBorder(GOLD));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFont(new Font("Serif", Font.PLAIN, 20));
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ConfirmLoadButtonListener());
    }

    private class ConfirmLoadButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute load option
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.loadData();
        }
    }
}
