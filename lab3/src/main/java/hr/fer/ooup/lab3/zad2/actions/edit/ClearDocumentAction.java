package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearDocumentAction extends AbstractAction {

    private TextEditorModel model;

    public ClearDocumentAction(TextEditorModel model) {
        this.model = model;

        this.putValue(Action.NAME, "Clear document");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (model.getLines().isEmpty()) {
            return;
        }

        model.clearDocument();

        return;

    }
}
