package main.java.ooup.lab2.zadatak6;

import main.java.ooup.lab2.zadatak6.listener.CellListener;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    public class ConcreteCellListener implements CellListener {
        @Override
        public void cellChanged() {
            updateCache();
        }
    }

    private String exp;
    private double value;
    private Sheet sheet;
    private ConcreteCellListener concreteCellListener;


    private List<CellListener> listeners;
    private List<Cell> referencedFields;


    public Cell(String exp, Sheet sheet) {
        this.sheet = sheet;
        this.exp = exp;
        this.value = 0;
        this.listeners = new ArrayList<>();
        this.referencedFields = new ArrayList<>();
        this.concreteCellListener = new ConcreteCellListener();
    }

    public void setRef(String ref) {

        if (ref.isEmpty()) {
            throw new IllegalArgumentException("The reference for the field must not be empty");
        }

        this.exp = ref;
        removeListenersFromOldReferences();
        List<Cell> newReferencedField = parse(ref);
        setReferencedFields(newReferencedField);

        updateCache();
    }

    public void updateCache() {

        double tempValue = 0;
        if (referencedFields.isEmpty()) {
            tempValue = Double.parseDouble(this.exp);
        } else {
            for (Cell referencedField : referencedFields) {
                tempValue += referencedField.getValue();
            }
        }

        this.value = tempValue;
        notifyAllListeners();

    }

    private void checkCircularDependency(List<Cell> cellsToBeChecked) {
        //TODO dodaj nekako da provjerava circular dependency
    }

    public void notifyAllListeners() {
        this.listeners.forEach(l -> l.cellChanged());
    }

    public void addListener(CellListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(CellListener listener) {
        this.listeners.remove(listener);
    }

    public double getValue() {
        return value;
    }

    public ConcreteCellListener getConcreteCellListener() {
        return concreteCellListener;
    }

    public List<Cell> getReferencedFields() {
        return referencedFields;
    }

    public void removeListenersFromOldReferences() {
        this.referencedFields.forEach(f -> f.removeListener(this.getConcreteCellListener()));
    }

    public void setReferencedFields(List<Cell> referencedFields) {
        this.referencedFields = referencedFields;
        referencedFields.forEach(f -> f.addListener(this.getConcreteCellListener()));
    }

    private List<Cell> parse(String exp) {

        exp = exp.trim();
        List<Cell> list = new ArrayList<>();

        if (exp.length() == 1) {
            return list;
        }

        String[] refs = exp.split("\\+");

        for (String ref : refs) {
            list.add(this.sheet.cell(ref));
        }

        return list;
    }

}
