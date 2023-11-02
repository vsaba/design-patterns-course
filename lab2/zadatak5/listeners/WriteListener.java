package main.java.ooup.lab2.zadatak5.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class WriteListener implements Listener {

    private Path path;

    public WriteListener(Path path) {
        this.path = path;
    }

    @Override
    public void numberChanged(List<Integer> numbers) {
        try {
            Files.writeString(this.path, new Timestamp(new Date().getTime()).toString());

            for (Integer number : numbers) {
                Files.writeString(this.path, number.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Written list to file: " + path);
    }
}
