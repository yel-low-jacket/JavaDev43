package com.finalproject.outputs;

import com.finalproject.model.Animal;
import com.finalproject.model.Barrel;
import com.finalproject.model.Human;
import com.finalproject.tracker.ObjectTracker;
import com.finalproject.service.Input;

import java.io.FileWriter;
import java.io.IOException;


public class Output {
    private static Input input;

    public static void outputAllArrays() {
        input = new Input();
        System.out.println("""
                1 - В консоль
                2 - В файл
                """);
        int choice = input.getValidIntInput();
        switch (choice){
            case 1 -> {
                System.out.println("Животные:");
                System.out.println(ObjectTracker.getCreatedAnimals());
                System.out.println("Человеки:");
                System.out.println(ObjectTracker.getCreatedHumans());
                System.out.println("Бочки:");
                System.out.println(ObjectTracker.getCreatedBarrels());
            }
            case 2 -> {
                try (FileWriter writer = new FileWriter("data.txt", true)) {
                    writer.write("\nНачало вывода:\n\n");
                    for (Animal ob : ObjectTracker.getCreatedAnimals()) {
                        writer.write(ob + "");  // Запись каждого элемента массива на новой строк
                    }
                    writer.write("\n");
                    for (Barrel ob : ObjectTracker.getCreatedBarrels()) {
                        writer.write(ob+"");  // Запись каждого элемента массива на новой строке
                    }
                    writer.write("\n");
                    for (Human ob : ObjectTracker.getCreatedHumans()) {
                        writer.write(ob+"");  // Запись каждого элемента массива на новой строке
                    }
                    writer.write("\n");
                    System.out.println("Массив добавлен в файл.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void outputAllArraysWithAdditionalSort() {
        input = new Input();
        System.out.println("""
                1 - В консоль
                2 - В файл
                """);
        int choice = input.getValidIntInput();
        switch (choice) {
            case 1: {
                System.out.println("Животные:");
                ObjectTracker.getCreatedAnimalWithSort();
                System.out.println("Человеки:");
                System.out.println(ObjectTracker.getCreatedHumanWithSort());
                System.out.println("Бочки:");
                System.out.println(ObjectTracker.getCreatedBarrelWithSort());
            }
            case 2:{
                try (FileWriter writer = new FileWriter("data.txt", true)) {
                    writer.write("\nНачало вывода:\n\n");
                    writer.write("Животные сортировке не подлежат.\n\n");
                    for (Barrel ob : ObjectTracker.getCreatedBarrelWithSort()) {
                        writer.write(ob+"");  // Запись каждого элемента массива на новой строке
                    }
                    writer.write("\n");
                    for (Human ob : ObjectTracker.getCreatedHumanWithSort()) {
                        writer.write(ob+"");  // Запись каждого элемента массива на новой строке
                    }
                    writer.write("\n");
                    System.out.println("Массив добавлен в файл.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
