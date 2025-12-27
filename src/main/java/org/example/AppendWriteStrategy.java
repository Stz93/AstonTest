package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class AppendWriteStrategy implements WriteStrategy{
    @Override
    public void writeToFile(String filePath, CustomArrayList<Student> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("There is no data to write to the file: " + filePath);
            return;
        }
        try(FileWriter writer = new FileWriter(filePath, true)){
            for(int i = 0; i < data.size(); i++){
                Student student = data.get(i);
                writer.write(student.toString() + '\n');
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
