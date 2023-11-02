package hr.fer.ooup.lab3.zad1.model;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Animal parrot = AnimalFactory.newInstance("Parrot", "Ofelija");

        parrot.animalPrintGreeting();
        parrot.animalPrintMenu();

        System.out.println();

        Animal tiger = AnimalFactory.newInstance("Tiger", "Jozefina");
        tiger.animalPrintGreeting();
        tiger.animalPrintMenu();
    }
}
