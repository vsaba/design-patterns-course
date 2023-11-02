package hr.fer.ooup.lab3.zad2.undo;

import hr.fer.ooup.lab3.zad2.actions.edit.EditAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoManager {

    private Stack<EditAction> undoStack;
    private Stack<EditAction> redoStack;
    private List<UndoListener> listeners;


    private static UndoManager undoManagerInstance = new UndoManager();


    private UndoManager() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.listeners = new ArrayList<>();
    }

    public static UndoManager getInstance() {
        return undoManagerInstance;
    }


    public void undo() {


        if (undoStack.isEmpty()) {
            return;
        }
        EditAction action = undoStack.pop();

        action.execute_undo();
        redoStack.push(action);

        notifyUndoListeners();

        return;
    }

    public void redo() {

        if (redoStack.isEmpty()) {
            return;
        }

        EditAction action = redoStack.pop();
        undoStack.push(action);
        action.execute_do();

        notifyUndoListeners();

        return;
    }

    public void push(EditAction action) {

        redoStack.clear();
        undoStack.push(action);

        notifyUndoListeners();

    }

    public boolean undoStackIsEmpty() {
        return undoStack.isEmpty();
    }

    public boolean redoStackIsEmpty() {
        return redoStack.isEmpty();
    }

    public boolean addUndoListeners(UndoListener listener) {
        return listeners.add(listener);
    }

    public boolean removeUndoListener(UndoListener listener) {
        return listeners.remove(listener);
    }

    public void notifyUndoListeners() {
        listeners.forEach(UndoListener::undoChanged);

        return;
    }

}
