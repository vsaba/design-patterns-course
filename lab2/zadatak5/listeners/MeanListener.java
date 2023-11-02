package main.java.ooup.lab2.zadatak5.listeners;

import java.util.List;

public class MeanListener implements Listener {

    @Override
    public void numberChanged(List<Integer> numbers) {
        double mean = numbers.stream().mapToInt(Integer::intValue).sum() / (double) numbers.size();

        System.out.println("The mean of the list is: " + mean);

    }
}
