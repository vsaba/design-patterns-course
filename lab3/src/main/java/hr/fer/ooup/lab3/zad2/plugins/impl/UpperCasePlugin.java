package hr.fer.ooup.lab3.zad2.plugins.impl;

import hr.fer.ooup.lab3.zad2.clipboard.ClipboardStack;
import hr.fer.ooup.lab3.zad2.model.TextEditorModel;
import hr.fer.ooup.lab3.zad2.plugins.Plugin;
import hr.fer.ooup.lab3.zad2.undo.UndoManager;

import java.util.Iterator;

public class UpperCasePlugin implements Plugin {
    @Override
    public String getName() {

        return "Veliko Slovo";
    }

    @Override
    public String getDescription() {
        return "Pretvara svako prvo slovo riječi u dokumentu u veliko slovo";
    }

    @Override
    public void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) {

        String allText = new String();

        Iterator<String> iterator = model.allLines();

        while (iterator.hasNext()) {
            String line = iterator.next();

            String[] pom = line.split(" ");

            for (int i = 0; i < pom.length; i++) {

                if(pom.length == 1 && pom[i].isEmpty()){
                    break;
                }

                allText += pom[i].substring(0, 1).toUpperCase() + pom[i].substring(1);

                if (i + 1 != pom.length) {
                    allText += " ";
                }
            }

            allText += "\n";

        }


        model.clearDocument();
        model.insert(allText);

        return;

    }
}
