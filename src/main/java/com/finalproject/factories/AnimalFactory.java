package com.finalproject.factories;

import com.finalproject.model.Animal;

public class AnimalFactory {
    public static Animal createAnimal(String species,String eyeColor,boolean hasFur) {
        return new Animal.AnimalBuilder()
                .setSpecies(species)
                .setEyeColor(eyeColor)
                .setHasFur(hasFur)
                .build();
    }
}
