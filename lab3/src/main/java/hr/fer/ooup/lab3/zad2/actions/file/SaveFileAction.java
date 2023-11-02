package hr.fer.ooup.lab3.zad2.actions.file;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class SaveFileAction extends AbstractAction {

    private TextEditorModel model;

    public SaveFileAction(TextEditorModel model) {
        this.model = model;
        this.putValue(Action.NAME, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jc = new JFileChooser("/");
        jc.setDialogTitle("Save file");

        if (jc.showSaveDialog((new JLabel())) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path path = jc.getSelectedFile().toPath();

        Iterator<String> iterator = model.allLines();
        String fileText = new String();
        while (iterator.hasNext()) {
            fileText += iterator.next();

            if (iterator.hasNext()) {
                fileText += "\n";
            }
        }

        try {
            Files.writeString(path, fileText);
        } catch (IOException exception) {
            System.err.println("Error while saving the file");
            return;
        }


        return;

    }
}
