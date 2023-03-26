package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a custom button specifically for submit Entry option
public class SubmitEntryButton extends Button {
    private static final Color GOLD = new Color(174,154,127);
    private JTextField enterPayee;
    private JTextField enterAmount;
    private JTextField enterPurchaseType;
    private int choice;

    public SubmitEntryButton(VisualApp editor, JComponent parent, JTextField a, JTextField b, JTextField c,
                             int choice) {
        super(editor, parent);
        this.enterPayee = a;
        this.enterAmount = b;
        this.enterPurchaseType = c;
        this.choice = choice;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Submit!");
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
        button.addActionListener(new SubmitEntryButtonListener());
    }

    private class SubmitEntryButtonListener implements ActionListener {

        // EFFECTS: Calls method to execute option

        @Override
        public void actionPerformed(ActionEvent e) {
            editor.submitEntry(enterPayee,enterAmount, enterPurchaseType, choice);
        }
    }
}
