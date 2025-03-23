package com.finalproject.creator;
import com.finalproject.model.Barrel;
import com.finalproject.service.Input;

public class BarrelCreator implements ObjectCreator {
    private final Input input;

    public BarrelCreator(Input input) {
        this.input = input;  // сохраняем зависимость
    }

    @Override
    public void createObject() {
        System.out.println("Введите объем бочки (в литрах):");
        int volume = input.getValidIntInput();

        System.out.println("Введите что хранят в бочке:");
        String storedMaterial = input.getValidStringInput();

        System.out.println("Введите материал бочки:");
        String material = input.getValidStringInput();

        Barrel barrel = new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setStoredMaterial(storedMaterial)
                .setMaterial(material)
                .build();

        System.out.println("Бочка добавлена: " + barrel);
    }
}
