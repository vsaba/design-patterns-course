package main.java.ooup.lab2.zadatak5.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianListener implements Listener {

    @Override
    public void numberChanged(List<Integer> numbers) {

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);

        double median;

        if(sortedNumbers.size() % 2 == 0){
            median = (sortedNumbers.get(sortedNumbers.size() / 2 - 1) + sortedNumbers.get(sortedNumbers.size() / 2)) / 2;
        }
        else{
            median = sortedNumbers.get(sortedNumbers.size() / 2);
        }

        System.out.println("The median of the list is: " + median);

    }
}
