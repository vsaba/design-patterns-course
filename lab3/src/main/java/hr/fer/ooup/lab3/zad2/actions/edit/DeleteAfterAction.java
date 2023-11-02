package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAfterAction extends AbstractAction {

    private TextEditorModel model;

    public DeleteAfterAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        this.putValue(Action.NAME, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.model.getSelectionRange().hasSelection()) {
            this.model.deleteRange(model.getSelectionRange());
            return;
        }

        this.model.deleteAfter();

        return;

    }
}
