package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveCursorToEndAction extends AbstractAction {

    private TextEditorModel model;

    public MoveCursorToEndAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("END"));
        this.putValue(Action.NAME, "Cursor to document end");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.model.moveCursorToEnd();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.getSelectionRange().resetSelection();
        }

    }
}
