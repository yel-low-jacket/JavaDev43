package com.finalproject.creator;

import com.finalproject.model.Animal;
import com.finalproject.service.Input;

public class AnimalCreator implements ObjectCreator {
    private final Input input;

    public AnimalCreator(Input input) {
        this.input = input;  // сохраняем зависимость
    }

    @Override
    public void createObject() {
        System.out.println("Введите вид животного:");
        String species = input.getValidStringInput();

        System.out.println("Введите цвет глаз животного:");
        String eyeColor = input.getValidStringInput();

        System.out.println("Есть ли у него шерсть? (да/нет):");
        boolean hasFur = input.getBooleanInput();

        Animal animal = new Animal.AnimalBuilder()
                .setSpecies(species)
                .setEyeColor(eyeColor)
                .setHasFur(hasFur)
                .build();

        System.out.println("Животное добавлено: " + animal);
    }
}
