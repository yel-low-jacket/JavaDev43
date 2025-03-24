package com.finalproject.creator;
import com.finalproject.model.Person;
import com.finalproject.service.Input;

public class PersonCreator implements ObjectCreator {
    private final Input input;

    public PersonCreator(Input input) {
        this.input = input;
    }

    @Override
    public void createObject() {
        System.out.println("Введите пол человека:");
        String gender = input.getValidStringInput();

        System.out.println("Введите возраст человека:");
        int age = input.getValidIntInput();

        System.out.println("Введите его фамилию:");
        String surname = input.getValidStringInput();

        Person person = new Person.PersonBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();

        System.out.println("Человек добавлен: " + person);
    }
}

