package com.finalproject.app;

import com.finalproject.creator.ObjectCreator;
import com.finalproject.creator.AnimalCreator;
import com.finalproject.creator.BarrelCreator;
import com.finalproject.creator.PersonCreator;
import com.finalproject.service.Input;
import java.io.IOException;

public class Main {

    private static ObjectCreator animalCreator;
    private static ObjectCreator barrelCreator;
    private static ObjectCreator personCreator;
    private static Input input;

    public static void main(String[] args) {
        input = new Input();
        animalCreator = new AnimalCreator(input);
        barrelCreator = new BarrelCreator(input);
        personCreator = new PersonCreator(input);

        while (true) {
            try {
                System.out.println("""
                    Выберите действие:
                    1 - Ввести объекты вручную
                    2 - Выйти
                    """);

                String choice = input.getValidStringInput();

                switch (choice) {
                    case "1":
                        createObjectsManually();
                        break;
                    case "2":
                        System.out.println("Пока-пока...");
                        return;
                    default:
                        System.out.println("Не надо так! Введите число от 1 до 2.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createObjectsManually() throws IOException {
        System.out.println("Выберите тип объекта для создания:");
        System.out.println("1 - Животное");
        System.out.println("2 - Бочка");
        System.out.println("3 - Человек");

        String choice = input.getValidStringInput();

        switch (choice) {
            case "1":
                animalCreator.createObject();
                break;
            case "2":
                barrelCreator.createObject();
                break;
            case "3":
                personCreator.createObject();
                break;
            default:
                System.out.println("Некорректный ввод!");
        }
    }
}