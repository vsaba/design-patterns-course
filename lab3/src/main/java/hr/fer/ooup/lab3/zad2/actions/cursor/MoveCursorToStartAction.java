package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveCursorToStartAction extends AbstractAction {

    private TextEditorModel model;

    public MoveCursorToStartAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("HOME"));
        this.putValue(Action.NAME, "Cursor to document start");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.model.moveCursorToStart();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.getSelectionRange().resetSelection();
        }

    }
}
