package com.finalproject.model;
import java.util.Comparator;

public class Barrel {
    private final int volume;
    private final String storedMaterial;
    private final String material;

    private Barrel(BarrelBuilder barrelBuilder) {
        this.volume = barrelBuilder.volume;
        this.storedMaterial = barrelBuilder.storedMaterial;
        this.material = barrelBuilder.material;
    }

    public static class BarrelBuilder {
        private int volume;
        private String storedMaterial;
        private String material;

        public BarrelBuilder setVolume(int volume) {
            this.volume = volume;
            return this;
        }

        public BarrelBuilder setStoredMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public BarrelBuilder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }

    public static Comparator<Barrel> getComparator() {
        return Comparator.comparingInt((Barrel b) -> b.volume)
                .thenComparing(b -> b.storedMaterial)
                .thenComparing(b -> b.material);
    }

    @Override
    public String toString() {
        return String.format("Бочка { Объем: %d л, Хранимый материал: %s, Материал бочки: %s }",
                volume, storedMaterial, material);
    }
}