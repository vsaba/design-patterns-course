package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.location.LocationRange;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class PasteAction extends AbstractAction {

    private TextEditorModel model;
    private ClipboardStack stack;

    public PasteAction(TextEditorModel model, ClipboardStack stack) {
        this.model = model;
        this.stack = stack;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        this.putValue(Action.NAME, "Paste");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (stack.isEmpty()) {
            return;
        }

        String text = stack.peek();
        this.model.insert(text);

        return;
    }
}
