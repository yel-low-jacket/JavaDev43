package com.finalproject.creator;
import com.finalproject.model.Animal;
import com.finalproject.model.Barrel;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;

import java.util.List;
import java.util.Random;

public class BarrelCreator implements ObjectCreator<Barrel> {
    private final Input input;

    public BarrelCreator(Input input) {
        this.input = input;
    }

    @Override
    public Barrel createObject() {
        System.out.println("Введите объем бочки (в литрах):");
        int volume = input.getValidIntInput();

        System.out.println("Введите что хранят в бочке:");
        String storedMaterial = input.getValidStringInput();

        System.out.println("Введите материал бочки:");
        String material = input.getValidStringInput();

        Barrel barrel = new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setStoredMaterial(storedMaterial)
                .setMaterial(material)
                .build();
        if (input.getMode().equals("createNew")){
            ObjectTracker.addBarrel(barrel);  // Track
        System.out.println("Бочка добавлена: " + barrel);
        }
        return barrel;
    }
    @Override
    public void createObjectFromString(String fields){
        String[] parts = fields.trim().toLowerCase().split("\\s+");
        int volume = -1;
        try {
            volume = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Бочка с объемом: " + volume + "не добавлена.\n" +
                    "Указанный объем не целочисленное число!\n");
        }
        String storedMaterial = parts[2];
        String material = parts[3];
        if (storedMaterial.matches("[a-zA-Zа-яА-ЯёЁ ]+") && material.matches("[a-zA-Zа-яА-ЯёЁ ]+")) {
            Barrel barrel = new Barrel.BarrelBuilder()
                    .setVolume(volume)
                    .setStoredMaterial(storedMaterial)
                    .setMaterial(material)
                    .build();
            ObjectTracker.addBarrel(barrel);  // Track
        }
        else{
            System.out.println("Введена строка с числами. Введите строку без чисел!\n" +
                    "Объект Бочка: Материала " + material + " Наполненная материалом " + storedMaterial + " не добавлен!");
        }
    }
    public Barrel createRandomObject(){
        RandomObjectCreator creator = new RandomObjectCreator();
        Barrel barrel = creator.createRandomBarrel();
        ObjectTracker.addBarrel(barrel); // Track
        return barrel;
    }
}