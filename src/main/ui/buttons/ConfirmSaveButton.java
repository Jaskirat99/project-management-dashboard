package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmSaveButton extends Button {
    private static final Color GOLD = new Color(174,154,127);

    public ConfirmSaveButton(VisualApp editor, JComponent parent) {
        super(editor, parent);

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Yes, Save Data!");
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
        button.addActionListener(new ConfirmSaveButtonListener());
    }

    private class ConfirmSaveButtonListener implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.saveState();
        }
    }
}
