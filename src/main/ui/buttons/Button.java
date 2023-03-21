package ui.buttons;

import ui.VisualApp;

import javax.swing.*;

public abstract class Button {
    protected JMenuItem button;
    protected VisualApp editor;

    public Button(VisualApp editor, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // EFFECTS: creates a button
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }


}
