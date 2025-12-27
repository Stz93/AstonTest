package org.example;

public interface WriteStrategy {
    void writeToFile(String filePath, CustomArrayList<Student> data)
            throws DataLoadingException;
}
