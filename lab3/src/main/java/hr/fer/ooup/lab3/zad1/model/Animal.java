package hr.fer.ooup.lab3.zad1.model;

public abstract class Animal {


    public abstract String name();
    public abstract String greet();
    public abstract String menu();

    public void animalPrintGreeting(){
        System.out.println(name() + " kaze: " + greet());

        return;
    }

    public void animalPrintMenu(){
        System.out.println(name() + " voli jesti: " + menu());

        return;
    }
}
