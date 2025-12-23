package org.example;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomInputStrategy implements InputStrategy {
    private Random random = new Random();
    private final String[] names = {"Anastasia", "Bogdan", "Aleksei", "Viktor", "Viktoria"};
    private final int countRandom;

    public RandomInputStrategy(int countRandom) {
        if (countRandom <= 0) {
            throw new IllegalArgumentException("The count must be greater than zero");
        }
        this.countRandom = countRandom;
    }
    @Override
    public CustomArrayList<Student> loadData(){
        return Stream.generate(() -> createRandomStudent())
                .limit(countRandom)
                .collect(Collectors.toCollection(CustomArrayList::new));
        //перенес ввод количества в фабрику
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
//если тут напишу то можно будет кинуть снова