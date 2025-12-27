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
    public CustomArrayList<Student> loadData() {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(this::parseLine)
                    .filter(student -> student != null)
                    .collect(Collectors.toCollection(CustomArrayList::new));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new CustomArrayList<>();
        }
    }

    private Student parseLine(String line) {
        try {
            if (line == null || line.trim().isEmpty()) {
                System.err.println("Warning: Empty line skipped");
                return null;
            }

            String trimmedLine = line.trim();

            String[] parts = trimmedLine.split("\\t");

            if (parts.length < 3) {
                parts = trimmedLine.split("\\s{3,}");
            }

            if (parts.length < 3) {
                System.err.println("Warning: Invalid format in line (expected 3 parts): " + line);
                return null;
            }

            String namePart = parts[0].trim();
            String gradePart = parts[1].trim();
            String recordPart = parts[2].trim();

            String name = extractValue(namePart, "name :");
            if (name.isEmpty()) {
                System.err.println("Warning: Name not found in: " + namePart);
                return null;
            }

            String gradeStr = extractValue(gradePart, "averageGrade :");
            double averageGrade;
            try {
                averageGrade = Double.parseDouble(gradeStr);
            } catch (NumberFormatException e) {
                System.err.println("Warning: Invalid average grade format: " + gradeStr);
                return null;
            }

            if (averageGrade < 0 || averageGrade > 5) {
                System.err.println("Warning: Grade out of range (0-5): " + averageGrade);
                return null;
            }

            String recordBookStr = extractValue(recordPart, "recordBookNumber :");
            int recordBookNumber;
            try {
                recordBookNumber = Integer.parseInt(recordBookStr);
            } catch (NumberFormatException e) {
                System.err.println("Warning: Invalid record book number format: " + recordBookStr);
                return null;
            }

            if (recordBookNumber <= 0) {
                System.err.println("Warning: Invalid record book number (must be positive): " + recordBookNumber);
                return null;
            }

            return new Student.Builder()
                    .name(name)
                    .averageGrade(averageGrade)
                    .recordBookNumber(recordBookNumber)
                    .build();

        } catch (Exception e) {

            System.err.println("Warning: Unexpected error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }

    private String extractValue(String text, String prefix) {
        if (text.startsWith(prefix)) {
            return text.substring(prefix.length()).trim();
        }

        int colonIndex = text.indexOf(":");
        if (colonIndex != -1 && colonIndex < text.length() - 1) {
            return text.substring(colonIndex + 1).trim();
        }

        return "";
    }
}
