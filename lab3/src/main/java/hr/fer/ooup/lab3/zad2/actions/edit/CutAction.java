package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.location.LocationRange;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CutAction extends AbstractAction {

    private TextEditorModel model;
    private ClipboardStack stack;


    public CutAction(TextEditorModel model, ClipboardStack stack) {
        this.model = model;
        this.stack = stack;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        this.putValue(Action.NAME, "Cut");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LocationRange range = model.getSelectionRange();

        if (!range.hasSelection()) {
            return;
        }

        String text = model.getSelectedText(range);
        model.resetSelection();
        stack.push(text);
        model.deleteRange(range);

        return;

    }
}
