package com.finalproject.model;
import java.util.Comparator;

public class Animal {
    private final String species;
    private final String eyeColor;
    private final boolean hasFur;

    private Animal(com.finalproject.model.Animal.AnimalBuilder animalBuilder) {
        this.species = animalBuilder.species;
        this.eyeColor = animalBuilder.eyeColor;
        this.hasFur = animalBuilder.hasFur;
    }

    public static class AnimalBuilder {
        private String species;
        private String eyeColor;
        private boolean hasFur;

        public com.finalproject.model.Animal.AnimalBuilder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public com.finalproject.model.Animal.AnimalBuilder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public com.finalproject.model.Animal.AnimalBuilder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public com.finalproject.model.Animal build() {
            return new com.finalproject.model.Animal(this);
        }
    }

    public static Comparator<com.finalproject.model.Animal> getComparator() {
        return Comparator.comparing((com.finalproject.model.Animal a) -> a.species)
                .thenComparing(a -> a.eyeColor)
                .thenComparing(a -> a.hasFur);
    }

    @Override
    public String toString() {
        return String.format("Животное { Вид: %s, Цвет глаз: %s, Наличие шерсти: %s }",
                species, eyeColor, hasFur ? "есть" : "нет");
    }
}