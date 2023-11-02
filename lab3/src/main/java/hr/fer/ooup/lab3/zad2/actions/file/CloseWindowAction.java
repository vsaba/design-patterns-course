package hr.fer.ooup.lab3.zad2.actions.file;

import hr.fer.ooup.lab3.zad2.TextEditor;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloseWindowAction extends AbstractAction {

    private TextEditor editor;

    public CloseWindowAction(TextEditor editor) {
        this.editor = editor;
        this.putValue(Action.NAME, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.dispose();
    }
}
