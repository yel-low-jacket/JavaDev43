package com.finalproject.model;

import java.util.Comparator;

public class Human{
    private final String gender;
    private final int age;
    private final String surname;

    private Human(HumanBuilder humanBuilder) {
        this.gender = humanBuilder.gender;
        this.age = humanBuilder.age;
        this.surname = humanBuilder.surname;
    }

    public static class HumanBuilder { // add class Builder
        private String gender;
        private int age;
        private String surname;

        public HumanBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public HumanBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public HumanBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }

    public static Comparator<Human> getComparator() {
        return Comparator.comparing((Human h) -> h.gender)
                .thenComparingInt(h -> h.age)
                .thenComparing(h -> h.surname);
    }
    @Override
    public String toString() {
        return String.format("Человек { Пол: %s, Возраст: %d, Фамилия: %s }\n",
                gender, age, surname);
    }
}
