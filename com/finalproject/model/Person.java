package com.finalproject.model;

public class Person {
    private final String gender;
    private final int age;
    private final String surname;

    private Person(PersonBuilder personBuilder) {
        this.gender = personBuilder.gender;
        this.age = personBuilder.age;
        this.surname = personBuilder.surname;
    }

    public static class PersonBuilder { // Переименовали Builder
        private String gender;
        private int age;
        private String surname;

        public PersonBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Человек { Пол: %s, Возраст: %d, Фамилия: %s }",
                gender, age, surname);
    }
}