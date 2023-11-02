package hr.fer.ooup.lab3.zad2.model;

import hr.fer.ooup.lab3.zad2.location.Location;
import hr.fer.ooup.lab3.zad2.location.LocationRange;
import hr.fer.ooup.lab3.zad2.observers.CursorObserver;
import hr.fer.ooup.lab3.zad2.observers.TextObserver;

import java.util.Iterator;
import java.util.List;

public interface TextEditorModel {

    Iterator<String> allLines();

    Iterator<String> linesRange(int index1, int index2);

    boolean addCursorObserver(CursorObserver cursorObserver);

    boolean removeCursorObserver(CursorObserver cursorObserver);

    void notifyCursorObservers();

    void moveCursorLeft();

    void moveCursorRight();

    void moveCursorUp();

    void moveCursorDown();

    void moveSelection(String direction);

    void moveCursorToEnd();

    void moveCursorToStart();

    boolean addTextObserver(TextObserver textObserver);

    boolean removeTextObserver(TextObserver textObserver);

    void notifyTextObservers();

    void deleteBefore();

    void deleteAfter();

    void deleteRange(LocationRange locationRange);

    void clearDocument();

    LocationRange getSelectionRange();

    void setSelectionRange(LocationRange locationRange);

    void resetSelection();

    String getSelectedText(LocationRange locationRange);

    void insert(char c);

    void insert(String text);

    List<String> getLines();

    void setLines(List<String> lines);

    Location getCursorLocation();

    void setCursorLocation(Location location);

}
