package hr.fer.ooup.lab3.zad2.model.impl;

import hr.fer.ooup.lab3.zad2.actions.edit.EditAction;
import hr.fer.ooup.lab3.zad2.location.Location;
import hr.fer.ooup.lab3.zad2.location.LocationRange;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.observers.CursorObserver;
import hr.fer.ooup.lab3.zad2.observers.TextObserver;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DefaultTextEditorModel implements TextEditorModel {

    private List<String> lines;
    private Location cursorLocation;
    private LocationRange selectionRange;
    private List<CursorObserver> cursorObservers;
    private List<TextObserver> textObservers;
    private UndoManager undoManager;

    public DefaultTextEditorModel(String initalText) {
        this.lines = new ArrayList<>(Arrays.asList(initalText.split("\n")));
        this.cursorLocation = new Location(lines.size() - 1, lines.get(lines.size() - 1).length());
        this.selectionRange = new LocationRange();
        this.cursorObservers = new ArrayList<>();
        this.textObservers = new ArrayList<>();
        this.undoManager = UndoManager.getInstance();

    }

    @Override
    public Iterator<String> allLines() {
        return lines.iterator();
    }

    @Override
    public Iterator<String> linesRange(int index1, int index2) {
        Iterator<String> iterator = new Iterator<String>() {

            private int currentIndex = index1;

            @Override
            public boolean hasNext() {
                if (currentIndex > index2) {
                    return false;
                }

                if (currentIndex >= lines.size()) {
                    return false;
                }

                return true;
            }

            @Override
            public String next() {
                return lines.get(currentIndex++);
            }
        };

        return iterator;
    }

    @Override
    public boolean addCursorObserver(CursorObserver cursorObserver) {
        return this.cursorObservers.add(cursorObserver);
    }

    @Override
    public boolean removeCursorObserver(CursorObserver cursorObserver) {
        return this.cursorObservers.remove(cursorObserver);
    }

    @Override
    public void notifyCursorObservers() {
        this.cursorObservers.forEach((c) -> c.updateCursorLocation(this.cursorLocation));
    }

    @Override
    public void moveCursorLeft() {

        int cursorRow = cursorLocation.getRow();
        int cursorCol = cursorLocation.getCol();

        if (cursorRow == 0 && cursorCol == 0) {
            return;         //nema gore vise
        } else if (cursorCol == 0) {
            String line = lines.get(--cursorRow);
            cursorCol = line.length();
        } else if (cursorCol > 0) {
            cursorCol--;
        }

        cursorLocation.setRow(cursorRow);
        cursorLocation.setCol(cursorCol);
        notifyCursorObservers();

        return;


    }

    @Override
    public void moveCursorRight() {

        int cursorRow = cursorLocation.getRow();
        int cursorCol = cursorLocation.getCol();

        String line = lines.get(cursorRow);

        if (line.length() == cursorCol) {
            if (lines.size() == cursorRow + 1) {
                return;
            } else {
                cursorRow++;
                cursorCol = 0;
            }

        } else {
            cursorCol++;
        }


        cursorLocation.setRow(cursorRow);
        cursorLocation.setCol(cursorCol);

        notifyCursorObservers();

        return;
    }

    @Override
    public void moveCursorUp() {

        int cursorRow = cursorLocation.getRow();
        int cursorCol = cursorLocation.getCol();

        if (cursorRow == 0) {
            return;     //nema gore
        }

        String newLine = lines.get(--cursorRow);

        if (cursorCol > newLine.length()) {
            cursorCol = newLine.length();
        }


        cursorLocation.setRow(cursorRow);
        cursorLocation.setCol(cursorCol);

        notifyCursorObservers();

        return;

    }

    @Override
    public void moveCursorDown() {

        int cursorRow = cursorLocation.getRow();
        int cursorCol = cursorLocation.getCol();

        if (cursorRow + 1 == lines.size()) {
            return;
        }

        String line = lines.get(++cursorRow);

        if (cursorCol > line.length()) {
            cursorCol = line.length();      //stavi ga na kraj
        }

        cursorLocation.setRow(cursorRow);
        cursorLocation.setCol(cursorCol);

        notifyCursorObservers();

        return;

    }


    @Override
    public boolean addTextObserver(TextObserver textObserver) {
        return this.textObservers.add(textObserver);
    }

    @Override
    public boolean removeTextObserver(TextObserver textObserver) {
        return this.textObservers.remove(textObserver);
    }

    @Override
    public void notifyTextObservers() {
        this.textObservers.forEach(t -> t.updateText());
    }

    @Override
    public void deleteBefore() {
        //backspace

        EditAction action = new EditAction() {

            private Location oldLocation;
            private String deletedText;

            @Override
            public void execute_do() {

                int cursorRow = cursorLocation.getRow();
                int cursorCol = cursorLocation.getCol();

                String line = lines.remove(cursorRow);

                if (cursorCol == 0 && cursorRow == 0) {
                    lines.add(cursorRow, line);
                    return;     //na pocetku datoteke smo retka smo
                } else if (cursorCol == 0) {
                    //spoji redak iznad sa trenutnim retkom
                    String prevLine = lines.remove(--cursorRow);
                    lines.add(cursorRow, prevLine + line);
                    cursorLocation.setRow(cursorRow);
                    cursorLocation.setCol(prevLine.length());
                    this.deletedText = "\n";
                } else {
                    this.deletedText = String.valueOf(line.charAt(cursorCol - 1));
                    StringBuilder builder = new StringBuilder(line);
                    builder.deleteCharAt(cursorCol - 1);
                    line = builder.toString();
                    lines.add(cursorRow, line);
                    moveCursorLeft();
                }

                notifyTextObservers();
                oldLocation = cursorLocation.getCopy();
            }

            @Override
            public void execute_undo() {
                cursorLocation = this.oldLocation.getCopy();
                insertUndo(deletedText);

                notifyTextObservers();
            }
        };

        action.execute_do();

        undoManager.push(action);

        return;

    }

    @Override
    public void deleteAfter() {
        //del

        EditAction action = new EditAction() {

            private Location oldLocation;
            private String deletedText;


            @Override
            public void execute_do() {
                int cursorRow = cursorLocation.getRow();
                int cursorCol = cursorLocation.getCol();

                String line = lines.remove(cursorRow);

                if (cursorCol == line.length() && cursorRow == lines.size()) {
                    //na kraju datoteke smo
                    lines.add(cursorRow, line);
                    return;
                } else if (cursorCol == line.length()) {
                    //spoji ovaj redak sa onim ispod
                    String nextLine = lines.remove(cursorRow);
                    lines.add(cursorRow, line + nextLine);
                    deletedText = "\n";
                } else {
                    deletedText = String.valueOf(line.charAt(cursorCol));
                    StringBuilder builder = new StringBuilder(line);
                    builder.deleteCharAt(cursorCol);
                    line = builder.toString();
                    lines.add(cursorRow, line);
                }

                oldLocation = cursorLocation.getCopy();

                notifyTextObservers();
            }

            @Override
            public void execute_undo() {
                cursorLocation = oldLocation.getCopy();
                insertUndo(deletedText);

                notifyTextObservers();
            }
        };

        undoManager.push(action);
        action.execute_do();

        return;

    }

    @Override
    public void deleteRange(LocationRange locationRange) {

        //multiple selection

        EditAction action = new EditAction() {

            private String deletedText;
            private Location oldLocation;

            @Override
            public void execute_do() {
                LocationRange location = locationRange.sortSelection();
                deletedText = getSelectedText(location);
                oldLocation = location.getLocationStart().getCopy();

                Location start = location.getLocationStart();
                Location end = location.getLocationEnd();

                int startRow = start.getRow();
                int endRow = end.getRow();

                if (startRow == endRow) {
                    String line = lines.remove(startRow);
                    line = line.substring(0, start.getCol()) + line.substring(end.getCol());
                    lines.add(startRow, line);

                    cursorLocation.setCol(start.getCol());
                    selectionRange.resetSelection();

                    notifyTextObservers();
                    return;
                }

                String firstLine = lines.get(startRow);
                String lastLine = lines.get(endRow);
                String newLine = firstLine.substring(0, start.getCol()) + lastLine.substring(end.getCol());


                for (int i = startRow; i <= endRow; i++) {
                    lines.remove(startRow);
                }

                lines.add(startRow, newLine);

                cursorLocation.setRow(startRow);
                cursorLocation.setCol(start.getCol());


                selectionRange.resetSelection();


                notifyTextObservers();
            }

            @Override
            public void execute_undo() {
                cursorLocation.setRow(oldLocation.getRow());
                cursorLocation.setCol(oldLocation.getCol());

                insertUndo(deletedText);
            }
        };


        action.execute_do();
        undoManager.push(action);

        return;
    }

    @Override
    public void clearDocument() {
        Location start = new Location(0, 0);
        Location end = new Location(lines.size() - 1, lines.get(lines.size() - 1).length());


        if (start.equals(end)) {
            return;
        }

        deleteRange(new LocationRange(start, end));

    }

    @Override
    public LocationRange getSelectionRange() {
        return selectionRange.sortSelection();
    }

    @Override
    public void setSelectionRange(LocationRange locationRange) {
        this.selectionRange = locationRange;
    }

    @Override
    public void resetSelection() {
        this.selectionRange.resetSelection();
        notifyTextObservers();
    }

    @Override
    public void moveSelection(String direction) {
        if (!this.selectionRange.hasSelection()) {
            this.selectionRange.setLocationStart(new Location(this.cursorLocation.getRow(), this.cursorLocation.getCol()));
        }

        switch (direction.toUpperCase()) {
            case "UP":
                moveCursorUp();
                break;

            case "DOWN":
                moveCursorDown();
                break;
            case "LEFT":
                moveCursorLeft();
                break;
            case "RIGHT":
                moveCursorRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid direction for selection range");
        }

        this.selectionRange.setLocationEnd(new Location(this.cursorLocation.getRow(), this.cursorLocation.getCol()));

        notifyCursorObservers();

        return;
    }

    @Override
    public String getSelectedText(LocationRange locationRange) {
        if (!locationRange.hasSelection()) {
            throw new IllegalArgumentException("No text selected, cannot return text");
        }

        Location startLocation = locationRange.getLocationStart();
        Location endLocation = locationRange.getLocationEnd();

        int startRow = startLocation.getRow();
        int endRow = endLocation.getRow();

        Iterator<String> selectedLines = this.linesRange(startRow, endRow);

        String text = new String();
        int i = startRow;
        while (selectedLines.hasNext()) {
            String line = selectedLines.next();
            if (startRow == endRow) {
                text += line.substring(startLocation.getCol(), endLocation.getCol());
                break;
            }

            if (i == startRow) {
                text += line.substring(startLocation.getCol()) + "\n";
            } else if (i == endRow) {
                text += line.substring(0, endLocation.getCol());
            } else {
                text += line + "\n";
            }

            i++;
        }


        return text;
    }

    @Override
    public void insert(char c) {
        this.insert(String.valueOf(c));
    }


    @Override
    public void insert(String text) {

        EditAction action = new EditAction() {

            private LocationRange oldRange = new LocationRange();

            @Override
            public void execute_do() {

                if (selectionRange.hasSelection()) {
                    deleteRange(selectionRange);
                }

                oldRange.setLocationStart(cursorLocation.getCopy());

                int row = cursorLocation.getRow();
                int col = cursorLocation.getCol();

                String line = lines.remove(row);

                StringBuilder builder = new StringBuilder(line);
                builder.insert(col, text);
                line = builder.toString();


                if (text.contains("\n")) {
                    String[] pom = line.split("\n");
                    if (pom.length == 0) {
                        lines.add(row++, "");
                        lines.add(row, "");
                    } else if (pom.length == 1) {
                        lines.add(row++, pom[0]);
                        lines.add(row, "");
                    } else {
                        for (String str : pom) {
                            lines.add(row++, str);
                        }
                        row--;
                    }
                    col = lines.get(row).length();
                    cursorLocation.setRow(row);
                } else {
                    lines.add(row, line);
                    col += text.length();
                }

                cursorLocation.setCol(col);

                oldRange.setLocationEnd(cursorLocation.getCopy());

                notifyTextObservers();

            }

            @Override
            public void execute_undo() {

                deleteUndo(oldRange);

            }
        };

        undoManager.push(action);
        action.execute_do();

        return;
    }

    private void insertUndo(String text) {

        if (selectionRange.hasSelection()) {
            deleteRange(selectionRange);
        }

        int row = cursorLocation.getRow();
        int col = cursorLocation.getCol();

        String line = lines.remove(row);

        StringBuilder builder = new StringBuilder(line);
        builder.insert(col, text);
        line = builder.toString();


        if (text.contains("\n")) {
            String[] pom = line.split("\n");
            if (pom.length == 0) {
                lines.add(row++, "");
                lines.add(row, "");
            } else if (pom.length == 1) {
                lines.add(row++, pom[0]);
                lines.add(row, "");
            } else {
                for (String str : pom) {
                    lines.add(row++, str);
                }
                row--;
            }
            col = lines.get(row).length();
            cursorLocation.setRow(row);
        } else {
            lines.add(row, line);
            col += text.length();
        }

        cursorLocation.setCol(col);

        notifyTextObservers();

    }

    private void deleteUndo(LocationRange locationRange) {

        LocationRange location = locationRange.sortSelection();

        Location start = location.getLocationStart();
        Location end = location.getLocationEnd();

        int startRow = start.getRow();
        int endRow = end.getRow();

        if (startRow == endRow) {
            String line = lines.remove(startRow);
            line = line.substring(0, start.getCol()) + line.substring(end.getCol());
            lines.add(startRow, line);

            cursorLocation.setCol(start.getCol());
            selectionRange.resetSelection();

            notifyTextObservers();
            return;
        }

        String firstLine = lines.get(startRow);
        String lastLine = lines.get(endRow);
        String newLine = firstLine.substring(0, start.getCol()) + lastLine.substring(end.getCol());


        for (int i = startRow; i <= endRow; i++) {
            lines.remove(startRow);
        }

        lines.add(startRow, newLine);

        cursorLocation.setRow(startRow);
        cursorLocation.setCol(start.getCol());


        selectionRange.resetSelection();

        notifyTextObservers();
    }


    @Override
    public List<String> getLines() {
        return lines;
    }

    @Override
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public Location getCursorLocation() {
        return cursorLocation;
    }

    @Override
    public void setCursorLocation(Location cursorLocation) {
        this.cursorLocation = cursorLocation;
    }


    @Override
    public void moveCursorToEnd() {
        int row = lines.size() - 1;
        int col = lines.get(row).length();

        this.cursorLocation.setRow(row);
        this.cursorLocation.setCol(col);

        notifyCursorObservers();

        return;
    }

    @Override
    public void moveCursorToStart() {
        this.cursorLocation.setRow(0);
        this.cursorLocation.setCol(0);

        notifyCursorObservers();
    }

}

