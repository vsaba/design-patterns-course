package main.java.ooup.lab2.zadatak4.percentile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalPercentile implements Percentile {

    @Override
    public int calculatePercentile(List<Integer> numberList, double percentile) {

        List<Integer> sortedList = new ArrayList<>(numberList);
        Collections.sort(numberList);

        int n_p = (int) Math.ceil(((percentile * sortedList.size()) / 100) + 0.5);

        return sortedList.get(n_p - 1);
    }
}
