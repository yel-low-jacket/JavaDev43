package com.finalproject.creator;
import com.finalproject.model.Barrel;
import com.finalproject.service.Input;
import com.finalproject.tracker.ObjectTracker;

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
        ObjectTracker.addObject(barrel);  // Track
        System.out.println("Бочка добавлена: " + barrel);
    }
    @Override
    public void createObjectFromString(String fields){
        String[] parts = fields.trim().toLowerCase().split("\\s+");
        int volume = -1;
        try {
            volume = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for barrel (volume): " + volume);
        }
        String storedMaterial = parts[2];
        String material = parts[3];
        Barrel barrel = new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setStoredMaterial(storedMaterial)
                .setMaterial(material)
                .build();
        System.out.println("Бочка из файла добавлена: " + barrel);
    }
}
