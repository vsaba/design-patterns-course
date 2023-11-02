package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CursorRightAction extends AbstractAction {


    private TextEditorModel model;


    public CursorRightAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("RIGHT"));
        this.putValue(Action.NAME, "Move cursor right");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.model.moveCursorRight();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.resetSelection();
        }

    }
}
