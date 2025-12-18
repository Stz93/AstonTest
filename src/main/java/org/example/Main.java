package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Количество потоков выделенное для многопоточных операций
     */
    final static int THREAD_NUMBER = 4;
    /**
     * Флаг для прерывания выполнения приложения
     */
    static boolean flag = true;

    public static void main(String[] args) {
        List<Student> studentList = new CustomArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (flag) {
            try {
                processRequest(scanner, studentList);
            } catch (final IllegalStateException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Обработка запроса пользователя.
     *
     * @param scanner требуется передать {@link Scanner} читающий из консоли.
     * @throws IllegalStateException выбрасываем исключения для некорректных вводов в консоль.
     */
    public static void processRequest(Scanner scanner, List<Student> studentList) throws IllegalStateException {
        System.out.println("MENU :\t1 to input");
        System.out.println("\t\t2 to output");
        System.out.println("\t\t3 to sort");
        System.out.println("\t\t4 to count entries");
        System.out.println("\t\t0 to exit");


        String actionCode = scanner.next();

        switch (actionCode) {
            case "1":
                System.out.println("You have chosen to input");
                System.out.println("Which input method should be selected?");
                System.out.println("\t\t1 from File : " + "*File directory*");
                System.out.println("\t\t2 random");
                System.out.println("\t\t3 manually");
                System.out.println("\t\t4 back");

                String inputCode = scanner.next();
                input(inputCode, studentList);
                processRequest(scanner, studentList);
                return;
            case "2":
                System.out.println("You have chosen to write data in file : *File directory*");
                write();
                processRequest(scanner, studentList);
                return;
            case "3":
                System.out.println("You have chosen to sort");
                System.out.println("Which field should be selected for sorting?");
                System.out.println("\t\t1 name");
                System.out.println("\t\t2 averageGrade");
                System.out.println("\t\t3 recordBookNumber");
                System.out.println("\t\t4 only even recordBookNumber");
                System.out.println("\t\t5 back");

                String sortCode = scanner.next();
                sort(sortCode, studentList);
                processRequest(scanner, studentList);
                return;
            case "4":
                System.out.println("You have chosen to count entries. Please choose entry.");
                System.out.println("\t\tname : ");
                String name = scanner.next();
                System.out.println("\t\taverageGrade : ");
                String averageGrade = scanner.next();
                System.out.println("\t\trecordBookNumber : ");
                String recordBookNumber = scanner.next();

                count(name, averageGrade, recordBookNumber, THREAD_NUMBER);
                processRequest(scanner, studentList);
                return;
            case "0":
                System.out.println("exit");
                flag = false;
                return;
        }

        throw new IllegalStateException("Wrong action code. Choose 1-3 or 0.");
    }

    /**
     * Обработчик для ввода данных в коллекцию студентов.
     *
     * @param inputCode код стратегии ввода.
     */
    static void input(final String inputCode, List<Student> studentList) {
        // реализовать паттерн "Стратегия" по введённому коду стратегии, добавить валидацию этого кода.
        Student.Builder builder = new Student.Builder();
        studentList.add(builder.name("Sasha").averageGrade(0.97).recordBookNumber(100101).build());
        studentList.add(builder.name("Bogdan").averageGrade(0.99).recordBookNumber(888888).build());
        studentList.add(builder.name("Kirill").averageGrade(1.00).recordBookNumber(414141).build());
        studentList.add(builder.name("Tagir").averageGrade(0.98).recordBookNumber(366663).build());
    }

    /**
     * Обработчик для записи данных из коллекции в файл.
     */
    static void write() {
        // реализовать паттерн "Стратегия" по введённому коду стратегии, добавить валидацию этого кода.
        System.out.println("массив записан");
    }

    /**
     * Многопоточный счётчик элементов
     *
     * @param name             {@link Student#name} для поиска
     * @param averageGrade     {@link  Student#averageGrade} для поиска
     * @param recordBookNumber {@link Student#recordBookNumber} для поиска
     * @param threadNumber     выделенное количество потоков
     */
    static void count(final String name, final String averageGrade, final String recordBookNumber, final int threadNumber) {
        // реализовать счётчик
        System.out.println("Посчитан элемент : " + name + ", " + averageGrade + ", " + recordBookNumber);
    }

    /**
     * Обработчик для сортировки коллекции студентов.
     *
     * @param sortCode
     */
    static void sort(final String sortCode, final List<Student> studentList) {
        // реализовать 4 режима режима. По каждому из полей + сортировка только чётных зачётных книжек.
        System.out.println("массив отсортирован : " + sortCode);
        studentList.stream().sorted((a, b) -> a.name.compareTo(b.name)).forEach(System.out::println);
    }
}