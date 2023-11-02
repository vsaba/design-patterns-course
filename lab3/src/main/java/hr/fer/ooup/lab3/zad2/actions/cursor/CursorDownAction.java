package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CursorDownAction extends AbstractAction {

    private TextEditorModel model;

    public CursorDownAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("DOWN"));
        this.putValue(Action.NAME, "Move cursor down");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.model.moveCursorDown();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.resetSelection();
        }

        return;

    }
}
