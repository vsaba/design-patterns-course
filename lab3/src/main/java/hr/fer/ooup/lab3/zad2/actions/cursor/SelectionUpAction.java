package hr.fer.ooup.lab3.zad2.actions.cursor;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SelectionUpAction extends AbstractAction {

    private TextEditorModel model;

    public SelectionUpAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.SHIFT_DOWN_MASK));
        this.putValue(Action.NAME, "Move selection up");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.moveSelection("UP");

    }
}
