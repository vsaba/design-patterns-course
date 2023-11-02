package main.java.ooup.lab2.zadatak5.listeners;

import java.util.List;

public class SumListener implements Listener {

    @Override
    public void numberChanged(List<Integer> numbers) {
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();

        System.out.println("The sum of all elements in the list is: " + sum);

        return;
    }
}
