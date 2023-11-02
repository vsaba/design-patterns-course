package hr.fer.ooup.lab3.zad2.plugins;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

public interface Plugin {

    String getName();
    String getDescription(); //ime plugina

    void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack);
}
