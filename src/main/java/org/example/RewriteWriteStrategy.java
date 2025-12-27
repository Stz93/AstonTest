package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class RewriteWriteStrategy implements WriteStrategy{
    @Override
    public void writeToFile(String filePath, CustomArrayList<Student> data){
        if (data == null || data.isEmpty()) {
            System.out.println("Data is empty: " + filePath);
            return;
        }
        try(FileWriter writer = new FileWriter(filePath, false)){
            for(int i = 0; i < data.size(); i++){
                Student student = data.get(i);
                writer.write(student.toString() + '\n');
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
