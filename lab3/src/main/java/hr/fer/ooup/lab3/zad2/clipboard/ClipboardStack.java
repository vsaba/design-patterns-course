package hr.fer.ooup.lab3.zad2.clipboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClipboardStack {

    private Stack<String> clipboardStack;
    private List<ClipboardObserver> clipboardListeners;

    public ClipboardStack() {
        this.clipboardStack = new Stack<>();
        this.clipboardListeners = new ArrayList<>();
    }

    public void push(String text) {
        clipboardStack.push(text);
        notifyClipboardListeners();
    }

    public String pop() {
        String text = clipboardStack.pop();
        notifyClipboardListeners();
        return text;
    }

    public String peek() {
        String text = clipboardStack.peek();
        notifyClipboardListeners();
        return text;
    }

    public boolean isEmpty() {
        return clipboardStack.isEmpty();
    }

    public boolean addClipboardListener(ClipboardObserver listener) {
        return clipboardListeners.add(listener);
    }

    public boolean removeClipboardListener(ClipboardObserver listener) {
        return clipboardListeners.remove(listener);
    }

    private void notifyClipboardListeners() {
        clipboardListeners.forEach(l -> l.updateClipboard());
    }
}
