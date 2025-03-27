package com.finalproject.creator;
import java.util.List;
import java.util.Random;
import com.finalproject.model.Animal;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;

public class AnimalCreator implements ObjectCreator<Animal> {
    private final Input input;

    public AnimalCreator(Input input) {
        this.input = input;
    }

    @Override
    public Animal createObject() {
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
        if (input.getMode().equals("createNew")){
            ObjectTracker.addAnimal(animal);  // Track
            System.out.println("Животное добавлено: " + animal);
        }
        return animal;
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
                throw new IllegalArgumentException("Животное с данными о наличии шерсти: " + hasFur + "не добавлено.\n Укажите логическую переменную для данного поля!");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error parsing bool for animal: " + e.getMessage());
        }
        if (species.matches("[a-zA-Zа-яА-ЯёЁ ]+") && eyeColor.matches("[a-zA-Zа-яА-ЯёЁ ]+")) {

            Animal animal = new Animal.AnimalBuilder()
                    .setSpecies(species)
                    .setEyeColor(eyeColor)
                    .setHasFur(hasFur)
                    .build();
            ObjectTracker.addAnimal(animal);  // Track
        }
        else{
            System.out.println("Введена строка с числами. Введите строку без чисел!\n" +
                    "Объект Животное: Вида " + species + " с Цветом глаз " + eyeColor + " не добавлен!");
        }
    }
    public Animal createRandomObject(){
        RandomObjectCreator creator = new RandomObjectCreator();
        Animal animal = creator.createRandomAnimal();
        ObjectTracker.addAnimal(animal);  // Track
        return animal;
    }
}