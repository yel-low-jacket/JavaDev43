package com.finalproject.creator;

import java.io.IOException;

public interface ObjectCreator {
    void createObject() throws IOException;
    void createObjectFromString(String fields) throws IOException;
    void  createRandomObject() throws IOException;
}
