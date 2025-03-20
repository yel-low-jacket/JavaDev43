package com.finalproject.creator;
import com.finalproject.model.Human;
import com.finalproject.service.Input;

public class HumanCreator implements ObjectCreator {
    private final Input input;

    public HumanCreator(Input input) {
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

        Human human = new Human.HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();

        System.out.println("Человек добавлен: " + human);
    }
}

