package com.finalproject.model;

public class Animal {
    private final String species;
    private final String eyeColor;
    private final boolean hasFur;

    private Animal(AnimalBuilder animalBuilder) {
        this.species = animalBuilder.species;
        this.eyeColor = animalBuilder.eyeColor;
        this.hasFur = animalBuilder.hasFur;
    }

    public static class AnimalBuilder {
        private String species;
        private String eyeColor;
        private boolean hasFur;

        public AnimalBuilder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public AnimalBuilder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public AnimalBuilder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Животное { Вид: %s, Цвет глаз: %s, Наличие шерсти: %s }",
                species, eyeColor, hasFur ? "есть" : "нет");
    }
}