package main.java.ooup.lab2.zadatak4.generators;

import java.util.ArrayList;
import java.util.List;

public class SequentialNumberGenerator implements NumberGenerator {

    private int lowerBound;
    private int upperBound;
    private int step;

    public SequentialNumberGenerator(int lowerBound, int upperBound, int step) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
    }

    @Override
    public List<Integer> generateNumbers() {

        List<Integer> list = new ArrayList<>();

        for (int i = lowerBound; i < upperBound; i += step) {
            list.add(i);
        }

        return list;
    }


}
