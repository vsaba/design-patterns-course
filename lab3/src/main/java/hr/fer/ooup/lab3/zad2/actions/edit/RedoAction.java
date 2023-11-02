package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractAction {

    public RedoAction() {
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        this.putValue(Action.NAME, "Redo");

        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UndoManager.getInstance().redo();
    }
}
