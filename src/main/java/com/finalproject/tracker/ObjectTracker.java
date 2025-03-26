package com.finalproject.tracker;
import com.finalproject.TimSort.TimSort;
import com.finalproject.customarraylist.CustomArrayList;
import com.finalproject.model.Animal;
import com.finalproject.model.Barrel;
import com.finalproject.model.Human;

import java.util.ArrayList;
import java.util.List;

public class ObjectTracker {
    private static List<Animal> createdAnimals = new CustomArrayList<>();
    private static List<Human> createdHumans = new CustomArrayList<>();
    private static List<Barrel> createdBarrels = new CustomArrayList<>();

    public static void addHuman(Human human) {
        createdHumans.add(human);
        TimSort.timSort(createdHumans, Human.getComparator());
    }
    public static void addAnimal(Animal animal) {
        createdAnimals.add(animal);
        TimSort.timSort(createdAnimals, Animal.getComparator());
    }
    public static void addBarrel(Barrel barrel) {
        createdBarrels.add(barrel);
        TimSort.timSort(createdBarrels, Barrel.getComparator());
    }

    public static List<Animal> getCreatedAnimals() {
        return createdAnimals;
    }
    public static List<Human> getCreatedHumans() { return createdHumans; }
    public static List<Barrel> getCreatedBarrels() { return createdBarrels; }

    public static List<Human> getCreatedHumanWithSort() {
        TimSort.sortEvenNumbersWithTimsort(createdHumans,"age");
        return createdHumans;
    }
    public static void getCreatedAnimalWithSort() {
        System.out.println("Животные дополнительной сортировке не подлежат");
    }
    public static List<Barrel> getCreatedBarrelWithSort() {
        TimSort.sortEvenNumbersWithTimsort(createdBarrels, "volume");
        return createdBarrels;
    }

}
