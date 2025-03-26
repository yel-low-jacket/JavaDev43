package com.finalproject.creator;

import com.finalproject.model.Animal;
import com.finalproject.model.Barrel;
import com.finalproject.model.Human;
import java.util.Random;

public class RandomObjectCreator {

    private static final String[] ANIMAL_SPECIES = {"Кошка", "Собака", "Рептилия", "Земноводное", "Рыба"};
    private static final String[] ANIMAL_EYE_COLORS = {"Синий", "Зеленый", "Желтый", "Карий", "Голубой"};
    private static final String[] BARREL_STORED_MATERIALS = {"Вода", "Пиво", "Мед", "Масло"};
    private static final String[] BARREL_MATERIALS = {"Дуб", "Сталь", "Пластик", "Сосна", "Золото", "Серебро", "Береза"};
    private static final String[] HUMAN_GENDER = {"Мужской", "Женский", "Небинарный", "Гендерофлюид вертосексуал", "Не определился"};
    private static final String[] HUMAN_SURNAMES = {"Brown", "Hopkins", "Godfrey", "Smith","Иванов", "Петров", "Сидоров", "Коваль", "Моль"};

    private final Random random;

    public RandomObjectCreator() {
        this.random = new Random();
    }

    // Генерация случайного животного
    public Animal createRandomAnimal() {
        return new Animal.AnimalBuilder()
                .setSpecies(ANIMAL_SPECIES[random.nextInt(ANIMAL_SPECIES.length)])
                .setEyeColor(ANIMAL_EYE_COLORS[random.nextInt(ANIMAL_EYE_COLORS.length)])
                .setHasFur(random.nextBoolean())
                .build();
    }

    // Генерация случайной бочки
    public Barrel createRandomBarrel() {
        return new Barrel.BarrelBuilder()
                .setVolume(random.nextInt(200) + 1) // Объем от 1 до 200 литров
                .setStoredMaterial(BARREL_STORED_MATERIALS[random.nextInt(BARREL_STORED_MATERIALS.length)])
                .setMaterial(BARREL_MATERIALS[random.nextInt(BARREL_MATERIALS.length)])
                .build();
    }

    // Генерация случайного человека
    public Human createRandomHuman() {
        return new Human.HumanBuilder()
                .setGender(HUMAN_GENDER[random.nextInt(HUMAN_GENDER.length)])
                .setAge(random.nextInt(80) + 1) // Возраст от 1 до 80 лет
                .setSurname(HUMAN_SURNAMES[random.nextInt(HUMAN_SURNAMES.length)])
                .build();
    }

    @Override
    public String toString() {
        return "RandomObjectCreator{" +
                "random=" + random +
                '}';
    }

    // Генерация случайного объекта любого типа
    public Object createRandomObject() {
        int choice = random.nextInt(3); // Выбираем между Animal (0), Barrel (1), Human (2)
        return switch (choice) {
            case 0 -> createRandomAnimal();
            case 1 -> createRandomBarrel();
            default -> createRandomHuman();
        };


    }
}