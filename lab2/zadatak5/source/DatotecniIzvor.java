package main.java.ooup.lab2.zadatak5.source;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DatotecniIzvor extends Izvor {


    public DatotecniIzvor(String path) throws IOException {
        super(new Scanner(Paths.get(path)));
    }
}
