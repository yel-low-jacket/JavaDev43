package com.finalproject.service;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Input {

    private final BufferedReader stringReader = new BufferedReader(new InputStreamReader(System.in));

    Path path = Paths.get(System.getProperty("src/main/resources/UserFile.txt"));

    private final FileReader fileReader = new FileReader((path.toFile()));

    public Input() throws URISyntaxException, IOException {
    }

    public void readFile() {

        try {
            fileReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getValidStringInput() {
        try {
            return stringReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getValidIntInput() {
        while (true) {
            try {
                String input = stringReader.readLine();
                return Integer.parseInt(input);
            } catch (IOException | NumberFormatException e) {
                System.out.println("Не надо так! Введите число.");
            }
        }
    }


    public boolean getBooleanInput() {
        while (true) {
            try {
                String input = stringReader.readLine().trim().toLowerCase();
                if (input.equals("да")) return true;
                if (input.equals("нет")) return false;
                System.out.println("Не надо так! Введите 'да' или 'нет'.");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
