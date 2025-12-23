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
    public CustomArrayList<Student> loadData()  {
        return Stream.generate(()-> createStudent())
                .limit(countManual)
                .collect(Collectors.toCollection(CustomArrayList::new));

    }

    private Student createStudent()
    {
        System.out.println("Adding a new student");
        System.out.println("Name: ");
        String name = scannerManualInput.nextLine();

        System.out.println("Average grade: ");
        double averageGrade = scannerManualInput.nextDouble();

        scannerManualInput.nextLine();

        System.out.println("Number of record book: ");
        int recordBookNumber = scannerManualInput.nextInt();

        scannerManualInput.nextLine();

        return new Student.Builder()
                .name(name)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}