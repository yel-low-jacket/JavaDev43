package com.finalproject.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Input {
    private static String mode;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getMode() {
        return this.mode;
    }
    private String getInput() {
        while(true){
            try {
                String val = reader.readLine().trim();
                if (val.isEmpty()){
                    System.out.println("Введено пустое значение, попробуйте ещё раз!");
                    continue;
                }
                return val;

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Произошла ошибка, попробуйте ещё раз!");
            }
        }

    }
    public String getValidFileInput(){
     while(true){
         String filePath = getInput();
         try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
             return filePath;
     } catch (InvalidPathException e) {
            // Обработка неправильного пути
            System.err.println("Ошибка: Неправильный путь к файлу: " + filePath);
        } catch (IOException e) {
            // Обработка ошибок ввода-вывода
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
    }
    public String getValidStringInput() {
        while(true){
            String val = getInput();
            if (!val.matches("[a-zA-Zа-яА-ЯёЁ ]+")){
                System.out.println("Введена строка с числами. Введите строку без чисел!");
                continue;
            }
            return val;
        }

    }

    public int getValidIntInput() {
        while (true) {
            try {
                String input = getInput();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Не надо так! Введите число.");
            }
        }
    }

    public boolean getBooleanInput() {
        while (true) {
            String input = getInput().toLowerCase();
            switch (input) {
                case "да":
                    return true;
                case "нет":
                    return false;
                default:
                    System.out.println("Не надо так! Введите 'да' или 'нет'.");
            }
        }
    }
}
