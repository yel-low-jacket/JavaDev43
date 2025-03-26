package com.finalproject.app;

import com.finalproject.model.Barrel;
import com.finalproject.model.Human;
import com.finalproject.tracker.ObjectTracker;
import com.finalproject.creator.ObjectCreator;
import com.finalproject.creator.AnimalCreator;
import com.finalproject.creator.BarrelCreator;
import com.finalproject.creator.HumanCreator;
import com.finalproject.service.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.file.InvalidPathException;
import com.finalproject.TimSort.TimSort;
import java.util.Arrays;
import java.util.Comparator;

import java.net.URISyntaxException;


public class Main {

    private static ObjectCreator animalCreator;
    private static ObjectCreator barrelCreator;
    private static ObjectCreator personCreator;
    private static Input input;

    public static void main(String[] args) {

        input = new Input();

        animalCreator = new AnimalCreator(input);
        barrelCreator = new BarrelCreator(input);
        personCreator = new HumanCreator(input);

        while (true) {
            try {
                System.out.println("""
                    Выберите действие:
                    1 - Ввести объекты вручную
                    2 - Импортировать объекты из файла
                    3 - Создать случайные объекты
                    4 - Вывести отсортированные массивы объектов
                    5 - Бинарный поиск
                    6 - Дополнительная сортировка
                    7 - Выйти
                    """);

                String choice = input.getValidStringInput();

                switch (choice) {
                    case "1":
                        createObjectsManually();
                        break;
                    case "2":
                        createObjectsFromFile();
                        break;
                    case "3":
                        createRandomObjects();
                        break;
                    case "4":
                        outputAllArrays();
                        break;
                    case "5":

                        break;
                    case "6":
                        outputAllArraysWithAdditionalSort();
                        break;
                    case "7":
                        System.out.println("Пока-пока...");
                        return;
                    default:
                        System.out.println("Не надо так! Введите число от 1 до 7.");
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
    private static void createObjectsFromFile() throws IOException {
        System.out.println("Введите полный путь к файлу. Наполнение файла должно иметь следующий вид: \n" +
                "Имя объекта Поле1 Поле2 Поле3.\n" +
                "Последовательность полей для классов: \n" +
                "Животное Вид Цвет глаз Шерст \n" +
                "Бочка Объем Хранимый материал Материал изготовления \n" +
                "Человек Пол Возраст Фамилия");
        String filePath = input.getValidStringInput();

        try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
            String[] linesArray = linesStream.toArray(String[]::new); // Лист строк данных классов и его полей для дальнейшего ввода

            for (String line : linesArray){
                String className = line.trim().toLowerCase().split("\\s+")[0];
                switch (className) {
                    case "животное":
                        animalCreator.createObjectFromString(line);
                        break;
                    case "бочка":
                        barrelCreator.createObjectFromString(line);
                        break;
                    case "человек":
                        personCreator.createObjectFromString(line);
                        break;
                }
            }
        } catch (InvalidPathException e) {
            // Обработка неправильного пути
            System.err.println("Ошибка: Неправильный путь к файлу: " + filePath);
        } catch (IOException e) {
            // Обработка ошибок ввода-вывода
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        }
        private static void createRandomObjects() throws IOException{
            System.out.println("Введите количество рандомно добавляемых объектов во все таблицы");
            int amount = input.getValidIntInput();
            while (amount > 0) {
                animalCreator.createRandomObject();
                barrelCreator.createRandomObject();
                personCreator.createRandomObject();
                amount--;
            }
            System.out.println("Случайные объекты созданы.\n");
        }

        private static void outputAllArrays(){
            System.out.println("Животные:");
            System.out.println(ObjectTracker.getCreatedAnimals());
            System.out.println("Человеки:");
            System.out.println(ObjectTracker.getCreatedHumans());
            System.out.println("Бочки:");
            System.out.println(ObjectTracker.getCreatedBarrels());
        }

        private static void outputAllArraysWithAdditionalSort(){
            System.out.println("Животные:");
            ObjectTracker.getCreatedAnimalWithSort();
            System.out.println("Человеки:");
            System.out.println(ObjectTracker.getCreatedHumanWithSort());
            System.out.println("Бочки:");
            System.out.println(ObjectTracker.getCreatedBarrelWithSort());
        }

    }