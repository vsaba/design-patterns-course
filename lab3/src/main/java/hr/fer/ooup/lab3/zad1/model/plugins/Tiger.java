package hr.fer.ooup.lab3.zad1.model.plugins;

import hr.fer.ooup.lab3.zad1.model.Animal;

public class Tiger extends Animal {

    private String name;

    public Tiger(String name){
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String greet() {
        return "Bok ja sam macka";
    }

    @Override
    public String menu() {
        return "Volim jesti whiskas";
    }
}
