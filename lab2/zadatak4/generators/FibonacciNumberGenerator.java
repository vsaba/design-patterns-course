package main.java.ooup.lab2.zadatak4.generators;

import java.util.ArrayList;
import java.util.List;

public class FibonacciNumberGenerator implements NumberGenerator {

    private int numElements;

    public FibonacciNumberGenerator(int numElements) {
        this.numElements = numElements;
    }

    @Override
    public List<Integer> generateNumbers() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        for (int i = 2; i < numElements; i++) {
            list.add(list.get(i - 2) + list.get(i - 1));
        }

        return list;
    }

}
