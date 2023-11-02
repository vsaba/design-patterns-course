package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CursorUpAction extends AbstractAction {

    private TextEditorModel model;

    public CursorUpAction(TextEditorModel model) {
        this.model = model;


        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("UP"));
        this.putValue(Action.NAME, "Move cursor up");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.model.moveCursorUp();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.resetSelection();
        }

    }
}
