package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CursorLeftAction extends AbstractAction {


    private TextEditorModel model;


    public CursorLeftAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("LEFT"));
        this.putValue(Action.NAME, "Move cursor left");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.moveCursorLeft();

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.resetSelection();
        }

    }
}
