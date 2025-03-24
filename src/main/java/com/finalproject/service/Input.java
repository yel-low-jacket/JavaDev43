package com.finalproject.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Input {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String getValidStringInput() {
        try {
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getValidIntInput() {
        while (true) {
            try {

                String input = reader.readLine();

                return Integer.parseInt(input);
            } catch (IOException | NumberFormatException e) {
                System.out.println("Не надо так! Введите число.");
            }
        }
    }

    public boolean getBooleanInput() {
        while (true) {
            try {
                String input = reader.readLine().trim().toLowerCase();
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
