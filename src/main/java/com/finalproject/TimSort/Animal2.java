package com.finalproject.TimSort;

import java.util.Comparator;

public class Animal2 {
    private String species;
    private String eyeColor;
    private boolean hasFur;

    public Animal2(String species, String eyeColor, boolean hasFur) {
        this.species = species;
        this.eyeColor = eyeColor;
        this.hasFur = hasFur;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHasFur(boolean hasFur) {
        this.hasFur = hasFur;
    }

    @Override
    public String toString() {
        return String.format("Животное { Вид: %s, Цвет глаз: %s, Наличие шерсти: %s }",
                species, eyeColor, hasFur ? "есть" : "нет");
    }

    public static Comparator<Animal2> getComparator() {
        return Comparator.comparing(Animal2::getSpecies)
                .thenComparing(Animal2::getEyeColor)
                .thenComparing(Animal2::isHasFur, Comparator.reverseOrder());
    }
}
