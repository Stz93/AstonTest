package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class RewriteWriteStrategy implements WriteStrategy{
    @Override
    public void writeToFile(String filePath, CustomArrayList<Student> data) throws DataLoadingException {
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
            throw new DataLoadingException("Incorrect data");
        }
    }

    @Override
    public String getSupportedExtension() {
        return "";
    }
}
