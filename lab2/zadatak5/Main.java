package main.java.ooup.lab2.zadatak5;

import main.java.ooup.lab2.zadatak5.listeners.*;
import main.java.ooup.lab2.zadatak5.source.DatotecniIzvor;
import main.java.ooup.lab2.zadatak5.source.Izvor;
import main.java.ooup.lab2.zadatak5.source.TipkovnickiIzvor;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        Izvor keyboardSource = new TipkovnickiIzvor();
        Izvor datotecniIzvor = new DatotecniIzvor("./");
        Listener mean = new MeanListener();
        Listener median = new MedianListener();
        Listener sum = new SumListener();
        Listener write = new WriteListener(Paths.get("./"));



        SlijedBrojeva slijed = new SlijedBrojeva(keyboardSource);
        slijed.addListener(mean);
        slijed.addListener(median);
        slijed.addListener(sum);
        slijed.addListener(write);

        slijed.start();


    }
}
