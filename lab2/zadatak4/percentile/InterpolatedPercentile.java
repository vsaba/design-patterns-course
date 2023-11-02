package main.java.ooup.lab2.zadatak4.percentile;

import java.util.List;

public class InterpolatedPercentile implements Percentile {

    @Override
    public int calculatePercentile(List<Integer> numberList, double percentile) {

        double previous = 0;

        if (p(1, numberList.size()) > percentile) {
            return numberList.get(0);
        }

        if (p(numberList.size(), numberList.size()) < percentile) {
            return numberList.get(numberList.size() - 1);
        }

        for (int i = 0; i < numberList.size(); i++) {
            double next = p(i + 1, numberList.size());

            if (previous < percentile && next >= percentile) {
                return (int) Math.round(numberList.get(i - 1) + ((numberList.size() * (percentile - previous) * (numberList.get(i) - numberList.get(i - 1))) / 100));
            }

            previous = next;
        }

        return numberList.get(numberList.size() - 1);
    }


    private double p(int i, int N) {

        return (100 * (i - 0.5)) / N;
    }
}
