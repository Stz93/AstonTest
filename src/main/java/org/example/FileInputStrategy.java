package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileInputStrategy implements InputStrategy {
    String filePath;
    public FileInputStrategy(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public CustomArrayList<Student> loadData()
    {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(this::parseLine)
                    .collect(Collectors.toCollection(CustomArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Student parseLine(String line)
    {

        String[] parts = line.split(",");
        try{
            if (parts.length != 3) {
            } } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        String name = parts[0].trim();
        double averageGrade = Double.parseDouble(parts[1].trim());
        int recordBookNumber = Integer.parseInt(parts[2].trim());


        return new Student.Builder()
                .name(name)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}
