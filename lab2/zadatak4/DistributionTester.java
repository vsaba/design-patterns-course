package main.java.ooup.lab2.zadatak4;

import main.java.ooup.lab2.zadatak4.generators.FibonacciNumberGenerator;
import main.java.ooup.lab2.zadatak4.generators.GaussianNumberGenerator;
import main.java.ooup.lab2.zadatak4.generators.NumberGenerator;
import main.java.ooup.lab2.zadatak4.generators.SequentialNumberGenerator;
import main.java.ooup.lab2.zadatak4.percentile.InterpolatedPercentile;
import main.java.ooup.lab2.zadatak4.percentile.NormalPercentile;
import main.java.ooup.lab2.zadatak4.percentile.Percentile;

import java.util.ArrayList;
import java.util.List;

public class DistributionTester {

    private NumberGenerator numberGenerator;
    private Percentile percentile;

    public void testDistribution() {

        List<Integer> numbers = numberGenerator.generateNumbers();

        System.out.println("Number list: " + numbers);

        for (int i = 10; i < 100; i += 10) {
            System.out.println(i + "th percentile: " + percentile.calculatePercentile(numbers, i));
        }

        System.out.println();

        return;
    }

    public NumberGenerator getNumberGenerator() {
        return numberGenerator;
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Percentile getPercentile() {
        return percentile;
    }

    public void setPercentile(Percentile percentile) {
        this.percentile = percentile;
    }

    public static void main(String[] args) {
        DistributionTester tester = new DistributionTester();

        List<NumberGenerator> generators = new ArrayList<>();
        generators.add(new SequentialNumberGenerator(0, 10, 1));
        generators.add(new GaussianNumberGenerator(10,0,1));
        generators.add(new FibonacciNumberGenerator(10));

        List<Percentile> percentiles = new ArrayList<>();
        percentiles.add(new NormalPercentile());
        percentiles.add(new InterpolatedPercentile());

        for(NumberGenerator generator : generators){
            for(Percentile percentile : percentiles){
                tester.setNumberGenerator(generator);
                tester.setPercentile(percentile);
                tester.testDistribution();
            }

        }

    }
}
