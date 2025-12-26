package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class AppendWriteStrategy implements WriteStrategy{
    @Override
    public void writeToFile(String filePath, CustomArrayList<Student> data) throws DataLoadingException {
        if (data == null || data.isEmpty()) {
            System.out.println("Нет данных для записи в файл: " + filePath);
            return;
        }
        try(FileWriter writer = new FileWriter(filePath, true)){
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
