package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteBeforeAction extends AbstractAction {


    private TextEditorModel model;

    public DeleteBeforeAction(TextEditorModel model) {
        this.model = model;
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
        this.putValue(Action.NAME, "Backspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (model.getSelectionRange().hasSelection()) {
            model.deleteRange(model.getSelectionRange());
            return;
        }

        model.deleteBefore();

        return;

    }
}
