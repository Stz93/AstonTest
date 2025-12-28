package org.example;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualInputStrategy implements InputStrategy{
    Scanner scannerManualInput = new Scanner(System.in);
    private final int countManual;

    public ManualInputStrategy(int countManual){
        if(countManual < 0){
            throw new IllegalArgumentException("The count must be greater than zero");
        }
        this.countManual = countManual;
    }
    @Override
    public CustomArrayList<Student> loadData() {
        return Stream.generate(()-> createStudent())
                .limit(countManual)
                .collect(Collectors.toCollection(CustomArrayList::new));

    }

    private Student createStudent()
    {
        double averageGrade;
        int recordBookNumber;
        System.out.println("Adding a new student");
        System.out.println("Name: ");
        String name = scannerManualInput.nextLine();

        System.out.println("Average grade (2.0 - 5.0): ");
        while (true) {
            String inputAverageGrade = scannerManualInput.nextLine();
            try {
                averageGrade = Double.parseDouble(inputAverageGrade);
                if (averageGrade >= 2.0 && averageGrade <= 5.0) {
                    break;
                } else {
                    System.out.print("Grade must be between 2.0 and 5.0. Try again: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("It must be a number. Try again: ");
            }
        }

        System.out.println("Number of record book (5 numbs e.g 11111): ");
        while (true) {
            String inputRecordBookNumber = scannerManualInput.nextLine();
            try {
                recordBookNumber = Integer.parseInt(inputRecordBookNumber);

                if (inputRecordBookNumber.length() == 5 && recordBookNumber >= 10000 && recordBookNumber <= 99999) {
                    break;
                } else {
                    System.out.print("Record book number must be exactly 5 digits. Try again: ");

                }
            } catch (NumberFormatException e) {
                System.out.print("It must be a number. Try again: ");

            }
        }

        return new Student.Builder()
                .name(name)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}