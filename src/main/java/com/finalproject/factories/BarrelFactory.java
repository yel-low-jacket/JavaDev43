package com.finalproject.factories;

import com.finalproject.model.Barrel;

public class BarrelFactory {
    public static Barrel createBarrel(int volume, String storedMaterial, String material) {
        return new Barrel.BarrelBuilder()
                .setVolume(volume)
                .setStoredMaterial(storedMaterial)
                .setMaterial(material)
                .build();
        }
    }