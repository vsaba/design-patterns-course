package main.java.ooup.lab2.zadatak4.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussianNumberGenerator implements NumberGenerator{

    private double mean;
    private double std;
    private int numElements;


    public GaussianNumberGenerator(int numElements, double mean, double std){
        this.mean = mean;
        this.std = std;
        this.numElements = numElements;
    }


    @Override
    public List<Integer> generateNumbers() {

        List<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numElements; i++) {
            list.add((int) Math.round(rand.nextGaussian() * this.std + this.mean));
        }

        return list;
    }
}
