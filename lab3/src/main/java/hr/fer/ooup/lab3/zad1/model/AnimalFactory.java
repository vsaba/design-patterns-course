package hr.fer.ooup.lab3.zad1.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {

    public static Animal newInstance(String animalKind, String name) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Animal> clazz = null;

        clazz = (Class<Animal>) Class.forName("hr.fer.ooup.lab3.zad1.model.plugins." + animalKind);

        Constructor<?> ctr = clazz.getConstructor(String.class);


        return (Animal) ctr.newInstance(name);
    }
}
