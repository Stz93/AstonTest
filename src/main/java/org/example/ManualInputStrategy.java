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

        System.out.println("Average grade: ");
        String inputAverageGrade = scannerManualInput.nextLine();
        try{
            averageGrade = Double.parseDouble(inputAverageGrade);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Number of record book: ");
        String inputRecordBookNumber = scannerManualInput.nextLine();
        try {
            recordBookNumber = Integer.parseInt(inputRecordBookNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return new Student.Builder()
                .name(name)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}