package com.finalproject.creator;
import java.util.List;
import java.util.Random;
import com.finalproject.model.Animal;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;

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
        ObjectTracker.addAnimal(animal);  // Track
        System.out.println("Животное добавлено: " + animal);
    }
    @Override
    public void createObjectFromString(String fields){
        String[] parts = fields.trim().toLowerCase().split("\\s+");
        String species = parts[1];
        String eyeColor = parts[2];
        boolean hasFur = false;
        try{
            if (parts[3].equals("true") || parts[3].equals("да")) {
                hasFur = true;
            }
             else {
                throw new IllegalArgumentException("Invalid boolean format for animal (hasFur): " + hasFur);
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error parsing bool for animal: " + e.getMessage());
        }

        Animal animal = new Animal.AnimalBuilder()
                .setSpecies(species)
                .setEyeColor(eyeColor)
                .setHasFur(hasFur)
                .build();
        ObjectTracker.addAnimal(animal);  // Track
    }
    public void createRandomObject(){
        RandomObjectCreator creator = new RandomObjectCreator();
        Animal animal = creator.createRandomAnimal();
        ObjectTracker.addAnimal(animal);  // Track

    }
}
