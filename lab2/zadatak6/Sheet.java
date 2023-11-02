package main.java.ooup.lab2.zadatak6;

import java.util.List;
import java.util.Locale;

public class Sheet {

    private static final String columnsIndexPom = "ABCDEFGHIJKLMNOPRSTUVZXY";

    private Cell[][] cells;
    private int rowLength;
    private int columnLength;

    public Sheet(int rowLength, int columnLength) {
        this.cells = new Cell[rowLength][columnLength];
        this.rowLength = rowLength;
        this.columnLength = columnLength;
    }

    public Cell cell(String ref) {

        if (ref.length() != 2) {
            throw new IllegalArgumentException("The length of the reference must not be greater than 2");
        }
        ref = ref.toUpperCase(Locale.ROOT);

        int columnIndex = columnsIndexPom.indexOf(ref.charAt(0));
        int rowIndex = Integer.parseInt(String.valueOf(ref.charAt(1))) - 1;

        if (rowIndex >= this.rowLength || columnIndex >= this.columnLength) {
            throw new IllegalArgumentException("Out of range for array");
        }

        if (cells[rowIndex][columnIndex] == null) {
            cells[rowIndex][columnIndex] = new Cell(ref, this);
        }

        return cells[rowIndex][columnIndex];
    }

    public void set(String ref, String content) {
        Cell cell = this.cell(ref);
        cell.setRef(content);

        return;
    }

    public List<Cell> getRefs(Cell cell) {

        return cell.getReferencedFields();
    }


    public void evaluate(Cell cell) {
        cell.updateCache();
    }

    @Override
    public String toString() {
        String s = "  ";

        for(int i = 0; i < columnLength; i++){
            s += String.format("%10c", columnsIndexPom.charAt(i));
        }

        s += "\n";

        for(int i = 0; i < rowLength; i++){
            s += String.format("%2d", i + 1);
            for(int j = 0; j < columnLength; j++){
                if(cells[i][j] == null){
                    s += String.format("%10d", 0);
                    continue;
                }

                s += String.format("%10.1f", cells[i][j].getValue());
            }

            s += "\n";
        }

        return s;
    }
}