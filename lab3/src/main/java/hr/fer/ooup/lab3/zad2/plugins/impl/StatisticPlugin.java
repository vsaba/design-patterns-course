package hr.fer.ooup.lab3.zad2.plugins.impl;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.plugins.Plugin;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import javax.swing.*;
import java.util.Iterator;

public class StatisticPlugin implements Plugin {

    @Override
    public String getName() {
        return "Statistika";
    }

    @Override
    public String getDescription() {
        return "Prikazuje koliko dokument ima redaka, riječi i slova u dokumentu";
    }

    @Override
    public void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) {

        int rowNumber = 0;
        int wordNumber = 0;
        int letterNumber = 0;

        Iterator<String> iterator = model.allLines();

        while (iterator.hasNext()) {
            String line = iterator.next();

            rowNumber++;
            wordNumber += line.split(" ").length;
            letterNumber += line.replace(" ", "").length();

        }


        JOptionPane.showMessageDialog(new JPanel(), "Number of rows: " + rowNumber + "; Number of words: " + wordNumber + "; Number of characters: " + letterNumber);


        return;

    }
}
