package com.finalproject.tracker;
import com.finalproject.customarraylist.CustomArrayList;

import java.util.ArrayList;
import java.util.List;

public class ObjectTracker {
    private static List<Object> createdObjects = new CustomArrayList<>();

    public static void addObject(Object object) {
        createdObjects.add(object);
    }

    public static List<Object> getCreatedObjects() {
        return createdObjects;
    }
}
