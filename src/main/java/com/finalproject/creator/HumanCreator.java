package com.finalproject.creator;
import com.finalproject.model.Human;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;

import java.util.List;
import java.util.Random;

public class HumanCreator implements ObjectCreator {
    private final Input input;

    public HumanCreator(Input input) {
        this.input = input;
    }

    @Override
    public void createObject() {
        System.out.println("Введите пол человека:");
        String gender = input.getValidStringInput();

        System.out.println("Введите возраст человека:");
        int age = input.getValidIntInput();

        System.out.println("Введите его фамилию:");
        String surname = input.getValidStringInput();

        Human human = new Human.HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();
        ObjectTracker.addObject(human);  // Track

        System.out.println("Человек добавлен: " + human);
    }
    @Override
    public void createObjectFromString(String fields){
        String[] parts = fields.trim().toLowerCase().split("\\s+");
        String gender = parts[1];
        int age = -1;
        try {
            age = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for human (age): " + age);
        }
        String surname = parts[3];
        Human human = new Human.HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();
        ObjectTracker.addObject(human);  // Track
    }
    public void createRandomObject(){
        Random random = new Random();
        List<String> listGen = List.of("Мужской", "Женский", "Небинарный", "Гендерофлюид вертосексуал", "Не определился");
        List<String> listSur = List.of("Иванов", "Петров", "Сидоров", "Коваль", "Моль");
        String gender = listGen.get(random.nextInt(listGen.size()));
        int age = random.nextInt(100) +1;
        String surname = listSur.get(random.nextInt(listSur.size()));
        Human human = new Human.HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();
        ObjectTracker.addObject(human);  // Track
    }
}

