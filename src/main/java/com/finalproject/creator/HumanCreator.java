package com.finalproject.creator;
import com.finalproject.factories.HumanFactory;
import com.finalproject.model.Human;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;


public class HumanCreator implements ObjectCreator<Human> {
    private final Input input;

    public HumanCreator(Input input) {
        this.input = input;
    }

    @Override
    public Human createObject() {
        System.out.println("Введите пол человека:");
        String gender = input.getValidStringInput();

        System.out.println("Введите возраст человека:");
        int age = input.getValidIntInput();

        System.out.println("Введите его фамилию:");
        String surname = input.getValidStringInput();

        Human human = HumanFactory.createHuman(gender, age, surname);
        if (input.getMode().equals("createNew")) {
            ObjectTracker.addHuman(human);  // Track
            System.out.println("Человек добавлен: " + human);
        }
        return human;
    }
    @Override
    public void createObjectFromString(String fields){
        String[] parts = fields.trim().toLowerCase().split("\\s+");
        String gender = parts[1];
        int age = -1;
        try {
            age = Integer.parseInt(parts[2]);
            String surname = parts[3];
            if (gender.matches("[a-zA-Zа-яА-ЯёЁ ]+") && surname.matches("[a-zA-Zа-яА-ЯёЁ ]+")) {
                Human human = HumanFactory.createHuman(gender, age, surname);
                ObjectTracker.addHuman(human);  // Track
            }
            else{
                System.out.println("Введена строка с числами. Введите строку без чисел!\n" +
                        "Объект Человек: Гендера " + gender + " с Фамилией " + surname + " не добавлен!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Человек возраста: " + parts[2] + " не добавлен.\n" +
                    "Указаный возраст не целочисленное число!\n");
        }


    }
    public Human createRandomObject(){
        RandomObjectCreator creator = new RandomObjectCreator();
        Human human = creator.createRandomHuman();
        ObjectTracker.addHuman(human);  // Track
        return human;
    }
}
