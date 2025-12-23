package org.example;

import java.io.IOException;

public interface WriteStrategy {
    void writeToFile(CustomArrayList<Student> students, String filePath) throws IOException;
}
