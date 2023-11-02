package main.java.ooup.lab2.zadatak5.source;

import java.util.Scanner;

public abstract class Izvor {

    private Scanner sc;

    public Izvor(Scanner sc) {
        this.sc = sc;
    }

    public int ucitaj() {

        if (sc.hasNext()) {
            return sc.nextInt();
        }

        return -1;
    }
}
