package com.finalproject.TimSort;

import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        Animal2 a1 = new Animal2("Птица","желтый", false);
        Animal2 a2 = new Animal2("Млекопитающее","карий", true);
        Animal2 a3 = new Animal2("Рыба","синий", false);
        Animal2 a4 = new Animal2("Насекомое","черный", false);
        Animal2 a5 = new Animal2("Млекопитающее","красный", false);


        ArrayList<Animal2> animals2 = new ArrayList<>(5);
        animals2.add(a1);
        animals2.add(a2);
        animals2.add(a3);
        animals2.add(a4);
        animals2.add(a5);

        //animals2.sort(Animal2.getComparator());
        TimSort.timSort(animals2, Animal2.getComparator());
        for (Animal2 animal : animals2) {
            System.out.println(animal);
        }
    }




}
