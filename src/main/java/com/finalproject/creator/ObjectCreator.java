package com.finalproject.creator;

import java.io.IOException;

public interface ObjectCreator<T> {
    T createObject() throws IOException;  // Теперь возвращает объект
    void createObjectFromString(String fields) throws IOException;
    T createRandomObject() throws IOException; // Теперь возвращает объект
}