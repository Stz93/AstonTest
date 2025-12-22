package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomInputStrategy implements InputStrategy {
    private Random random = new Random();
    private final String[] names = {"Анастасия", "Богдан", "Алексей", "Виктор", "Виктория"};

    @Override
    public CustomArrayList<Student> loadData() throws DataLoadingException, ValidationException {

        System.out.println("Введите количество рандомно сгенерированных студентов: ");
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        if (quantity <= 0) {
            throw new ValidationException("Количество должно быть положительным");
        }
        System.out.println("Вы ввели: " + quantity);

        return Stream.generate(() -> createRandomStudent())
                .limit(quantity)
                .collect(Collectors.toCollection(CustomArrayList::new));
        //та же тема со стримом как в manualinput
    }

    private String generateRandomName() {
        return names[random.nextInt(names.length)];
    }

    private double generateRandomGrade() {
        double grade = random.nextDouble();
        return Math.round(grade * 100.0) / 100.0;
    }

    private int generateRandomRecordBookNumber() {
        return  random.nextInt(100000);
    }

    private Student createRandomStudent() {
        String randomName = generateRandomName();
        double randomAverageGrade = generateRandomGrade();
        int randomRecordBookNumber = generateRandomRecordBookNumber();

        return new Student.Builder()
                .name(randomName)
                .averageGrade(randomAverageGrade)
                .recordBookNumber(randomRecordBookNumber)
                .build();
    }
}