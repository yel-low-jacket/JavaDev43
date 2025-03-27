package com.finalproject.app;
import com.finalproject.tracker.ObjectTracker;
import com.finalproject.creator.ObjectCreator;
import com.finalproject.creator.AnimalCreator;
import com.finalproject.creator.BarrelCreator;
import com.finalproject.creator.HumanCreator;
import com.finalproject.search.BinarySearch;
import com.finalproject.customarraylist.CustomArrayList;
import com.finalproject.model.Human;
import com.finalproject.model.Barrel;
import com.finalproject.model.Animal;
import com.finalproject.service.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.nio.file.InvalidPathException;
import java.util.Comparator;

public class Main {

    private static ObjectCreator animalCreator;
    private static ObjectCreator barrelCreator;
    private static ObjectCreator humanCreator;
    private static Input input;

    public static void main(String[] args) {

        input = new Input();

        animalCreator = new AnimalCreator(input);
        barrelCreator = new BarrelCreator(input);
        humanCreator = new HumanCreator(input);

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

                int choice = input.getValidIntInput();

                switch (choice) {
                    case 1:
                        input.setMode("createNew");
                        createObjectsManually();
                        break;
                    case 2:
                        createObjectsFromFile();
                        break;
                    case 3:
                        createRandomObjects();
                        break;
                    case 4:
                        outputAllArrays();
                        break;
                    case 5:
                        input.setMode("find");
                        binerySearch();
                        break;
                    case 6:
                        outputAllArraysWithAdditionalSort();
                        break;
                    case 7:
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

        int choice = input.getValidIntInput();

        switch (choice) {
            case 1:
                animalCreator.createObject();
                break;
            case 2:
                barrelCreator.createObject();
                break;
            case 3:
                humanCreator.createObject();
                break;
            default:
                System.out.println("Некорректный ввод!");
        }
    }

    private static void createObjectsFromFile() throws IOException {
        System.out.println("Введите полный путь к файлу. Наполнение файла должно иметь следующий вид: \n\n" +
                "Имя объекта Поле1 Поле2 Поле3.\n\n" +
                "Последовательность полей для классов: \n" +
                "Животное Вид Цвет глаз Шерсть \n" +
                "Бочка Объем Хранимый материал Материал изготовления \n" +
                "Человек Пол Возраст Фамилия");
        String filePath = input.getValidFileInput();
        Stream<String> linesStream = Files.lines(Paths.get(filePath));
        String[] linesArray = linesStream.toArray(String[]::new); // Лист строк данных классов и его полей для дальнейшего ввода
        for (String line : linesArray) {
            String className = line.trim().toLowerCase().split("\\s+")[0];
            switch (className) {
                case "животное":
                    animalCreator.createObjectFromString(line);
                    break;
                case "бочка":
                    barrelCreator.createObjectFromString(line);
                    break;
                case "человек":
                    humanCreator.createObjectFromString(line);
                    break;
            }
        }
        System.out.println("Объекты из файла созданы!");
    }

    private static void createRandomObjects() throws IOException {
        System.out.println("Выберите тип объекта для рандомного создания:");
        System.out.println("1 - Животное");
        System.out.println("2 - Бочка");
        System.out.println("3 - Человек");
        int choice = input.getValidIntInput();
        System.out.println("Введите количество рандомно добавляемых объектов");
        int amount = input.getValidIntInput();

        switch (choice) {
            case 1:
                while (amount > 0) {
                    animalCreator.createRandomObject();
                    amount--;
                }
                break;
            case 2:
                while (amount > 0) {
                    barrelCreator.createRandomObject();
                    amount--;
                }
                break;
            case 3:
                while (amount > 0) {
                    humanCreator.createRandomObject();
                    amount--;
                }
                break;
            default:
                System.out.println("Некорректный ввод!");
        }
        System.out.println("Случайные объекты созданы.\n");
    }

    private static <T> void binarySearch(List<T> list, ObjectCreator creator, Comparator<T> comparator) throws IOException {
        T searchObject = (T) creator.createObject();
        int index = BinarySearch.binarySearch(list, searchObject, comparator);

        if (index != -1) {
            System.out.println("Объект найден: " + list.get(index));
        } else {
            System.out.println("Объект не найден.");
        }
    }

    private static void binerySearch() throws IOException {
        System.out.println("""
                Выберите класс для поиска:
                1 - Животное
                2 - Бочка
                3 - Человек
                """);

        int choice = input.getValidIntInput();

        switch (choice) {
            case 1 -> binarySearch(ObjectTracker.getCreatedAnimals(), animalCreator, Animal.getComparator());
            case 2 -> binarySearch(ObjectTracker.getCreatedBarrels(), barrelCreator, Barrel.getComparator());
            case 3 -> binarySearch(ObjectTracker.getCreatedHumans(), humanCreator, Human.getComparator());
            default -> System.out.println("Некорректный ввод! Введите число от 1 до 3");
        }
    }

    private static void outputAllArrays() {
        System.out.println("Животные:");
        System.out.println(ObjectTracker.getCreatedAnimals());
        System.out.println("Человеки:");
        System.out.println(ObjectTracker.getCreatedHumans());
        System.out.println("Бочки:");
        System.out.println(ObjectTracker.getCreatedBarrels());
    }

    private static void outputAllArraysWithAdditionalSort() {
        System.out.println("Животные:");
        ObjectTracker.getCreatedAnimalWithSort();
        System.out.println("Человеки:");
        System.out.println(ObjectTracker.getCreatedHumanWithSort());
        System.out.println("Бочки:");
        System.out.println(ObjectTracker.getCreatedBarrelWithSort());
    }
}