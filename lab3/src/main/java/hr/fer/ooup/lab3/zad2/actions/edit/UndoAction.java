package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractAction {


    public UndoAction() {
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        this.putValue(Action.NAME, "Undo");

        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UndoManager.getInstance().undo();
    }
}
