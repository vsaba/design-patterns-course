package main.java.ooup.lab2.zadatak5;

import main.java.ooup.lab2.zadatak5.listeners.Listener;
import main.java.ooup.lab2.zadatak5.source.Izvor;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SlijedBrojeva {

    private List<Integer> numbers;
    private List<Listener> listeners;
    private Izvor source;


    public SlijedBrojeva(Izvor source){
        this.source = source;
        this.numbers = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public void start(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Please enter a number");
                int number = source.ucitaj();

                if(number == -1){
                    cancel();
                    return;
                }

                numbers.add(number);
                notifyListeners();
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }

    private void notifyListeners(){
        listeners.forEach(l -> l.numberChanged(this.numbers));
    }

    public void addListener(Listener listener){
        this.listeners.add(listener);
        return;
    }

    public void removeListener(Listener listener){
        this.listeners.remove(listener);
    }

    public Izvor getSource() {
        return source;
    }

    public void setSource(Izvor source) {
        this.source = source;
    }
}
