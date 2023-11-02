package hr.fer.ooup.lab3.zad2.actions.file;

import hr.fer.ooup.lab3.zad2.model.TextEditorModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenFileAction extends AbstractAction {

    private TextEditorModel model;

    public OpenFileAction(TextEditorModel model) {
        this.model = model;
        this.putValue(Action.NAME, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jc = new JFileChooser("/");
        jc.setDialogTitle("Open new file");

        if(jc.showOpenDialog(new JPanel()) != JFileChooser.APPROVE_OPTION){
            return;
        }

        Path path = jc.getSelectedFile().toPath();

        String newFile = null;
        try {
            newFile = Files.readString(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        model.clearDocument();
        model.insert(newFile);

        return;

    }
}
