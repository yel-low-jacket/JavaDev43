package com.finalproject.creator;

import com.finalproject.factories.AnimalFactory;
import com.finalproject.factories.BarrelFactory;
import com.finalproject.factories.HumanFactory;
import com.finalproject.model.Animal;
import com.finalproject.model.Barrel;
import com.finalproject.model.Human;
import java.util.Random;

public class RandomObjectCreator {

    private static final String[] ANIMAL_SPECIES = {"Кошка", "Собака", "Рептилия", "Земноводное", "Рыба"};
    private static final String[] ANIMAL_EYE_COLORS = {"Синий", "Зеленый", "Желтый", "Карий", "Голубой"};
    private static final String[] BARREL_STORED_MATERIALS = {"Вода", "Пиво", "Мед", "Масло", "Гвозди", "Гайки", "Материнсике платы"};
    private static final String[] BARREL_MATERIALS = {"Дуб", "Сталь", "Пластик", "Сосна", "Золото", "Серебро", "Береза"};
    private static final String[] HUMAN_GENDER = {"Мужской", "Женский", "Небинарный", "Гендерофлюид вертосексуал", "Не определился"};
    private static final String[] HUMAN_SURNAMES = {"Brown", "Hopkins", "Godfrey", "Smith","Иванов", "Петров", "Сидоров", "Коваль", "Моль"};

    private final Random random;

    public RandomObjectCreator() {
        this.random = new Random();
    }

    // Генерация случайного животного
    public Animal createRandomAnimal() {
        return AnimalFactory.createAnimal(
                ANIMAL_SPECIES[random.nextInt(ANIMAL_SPECIES.length)],
                ANIMAL_EYE_COLORS[random.nextInt(ANIMAL_EYE_COLORS.length)],
                random.nextBoolean()
        );
    }

    // Генерация случайной бочки
    public Barrel createRandomBarrel() {
        return BarrelFactory.createBarrel(
                random.nextInt(200) + 1,// Объем от 1 до 200 литров
                BARREL_STORED_MATERIALS[random.nextInt(BARREL_STORED_MATERIALS.length)],
                BARREL_MATERIALS[random.nextInt(BARREL_MATERIALS.length)]
        );
    }

    // Генерация случайного человека
    public Human createRandomHuman() {
        return  HumanFactory.createHuman(
                HUMAN_GENDER[random.nextInt(HUMAN_GENDER.length)],
                random.nextInt(80) + 1,
                HUMAN_SURNAMES[random.nextInt(HUMAN_SURNAMES.length)]
        );
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