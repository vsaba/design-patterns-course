package hr.fer.ooup.lab3.zad2.actions.edit;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class PasteAndRemoveAction extends AbstractAction {

    private TextEditorModel model;
    private ClipboardStack stack;

    public PasteAndRemoveAction(TextEditorModel model, ClipboardStack stack) {
        this.model = model;
        this.stack = stack;

        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        this.putValue(Action.NAME, "Paste and Take");
        this.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (stack.isEmpty()) {
            return;
        }

        String text = stack.pop();
        model.insert(text);

        return;

    }
}
