package org.example;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManualInputStrategy implements InputStrategy{
    Scanner scanner = new Scanner(System.in);
    @Override
    public CustomArrayList<Student> loadData() throws DataLoadingException, ValidationException {
        System.out.println("Введите количество вводимых сутдентов: ");

        int quantity = scanner.nextInt();
        if (quantity <= 0){
            throw new ValidationException("Введите положительное количество студентов");
        }
        scanner.nextLine();
        return Stream.generate(()-> createStudent())
                .limit(quantity)
                .collect(Collectors.toCollection(CustomArrayList::new));
        //перепроверить этот стримак после того как буду создавать ввод в файл, хз чето с интернета взял пока пусть так будет
    }

    private Student createStudent() // throws DataLoadingException, ValidationException - хотел сделать валидацию на ввод, но стримаку не нравится когда я вношу эти штуки сюда
    {
        System.out.println("Ввод нового студента");
        System.out.println("Имя: ");
        String name = scanner.nextLine();

        System.out.println("Средний балл: ");
        double averageGrade = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Введите номер зачетки: ");
        int recordBookNumber = scanner.nextInt();

        scanner.nextLine();

        return new Student.Builder()
                .name(name)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}
