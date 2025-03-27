package com.finalproject.factories;

import com.finalproject.model.Human;

public class HumanFactory {

    public static Human createHuman(String gender, int age, String surname) {
        return new Human.HumanBuilder()
                .setGender(gender)
                .setAge(age)
                .setSurname(surname)
                .build();
    }
}
