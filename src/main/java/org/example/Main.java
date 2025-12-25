package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    /**
     * Количество потоков выделенное для многопоточных операций
     */
    final static int NUM_THREADS = 4;
    /**
     * Флаг для прерывания выполнения приложения
     */
    static boolean flag = true;

    public static void main(String[] args) {
        CustomArrayList<Student> studentList = new CustomArrayList<>();
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
    public static void processRequest(final Scanner scanner,
                                      final CustomArrayList<Student> studentList) throws IllegalStateException {
        System.out.println("\n\nMENU :\t1 to input");
        System.out.println("\t\t2 to output");
        System.out.println("\t\t3 to sort");
        System.out.println("\t\t4 to count entries");
        System.out.println("\t\t5 to see collection");
        System.out.println("\t\t0 to exit");


        String actionCode = scanner.next();

        switch (actionCode) {
            case "1":
                System.out.println("\n\nYou have chosen to input");
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
                System.out.println("\n\nYou have chosen to write data in file : *File directory*");
                write();
                processRequest(scanner, studentList);
                return;
            case "3":
                System.out.println("\n\nYou have chosen to sort");
                System.out.println("Which field should be selected for sorting?");
                System.out.println("\t\t1 name");
                System.out.println("\t\t2 averageGrade");
                System.out.println("\t\t3 recordBookNumber");
                System.out.println("\t\t4 only even recordBookNumber");
                System.out.println("\t\t0 back");

                String sortCode = scanner.next();
                sort(sortCode, studentList);

                processRequest(scanner, studentList);
                return;
            case "4":
                System.out.println("\n\nYou have chosen to count entries. Please choose entry.");
                System.out.println("\t\tname : ");
                String name = scanner.next();
                System.out.println("\t\taverageGrade : ");
                String averageGrade = scanner.next();
                System.out.println("\t\trecordBookNumber : ");
                String recordBookNumber = scanner.next();

                try {
                    Student student = new Student(name, Double.parseDouble(averageGrade), Integer.parseInt(recordBookNumber));
                    System.out.println(count(student, studentList) + " elements were found for:");
                    System.out.println(student);
                } catch (NumberFormatException e) {
                    System.out.println("Values cant be cast to expected formats");
                }

                processRequest(scanner, studentList);
                return;
            case "5":
                System.out.println(studentList);
                return;
            case "0":
                System.out.println("\n\nexit");
                flag = false;
                return;
        }

        throw new IllegalStateException("Wrong action code. Choose 1-5 or 0.");
    }

    /**
     * Обработчик для ввода данных в коллекцию студентов.
     *
     * @param inputCode код стратегии ввода.
     */
    static void input(final String inputCode, final List<Student> studentList) {
        // реализовать паттерн "Стратегия" по введённому коду стратегии, добавить валидацию этого кода.
        Student.Builder builder = new Student.Builder();

        Student[] studentArray = new Student[6];
        studentArray[0] = builder.name("Sasha").averageGrade(0.97).recordBookNumber(100101).build();
        studentArray[1] = builder.name("Bogdan").averageGrade(0.99).recordBookNumber(888888).build();
        studentArray[2] = builder.name("Kirill").averageGrade(1.00).recordBookNumber(414141).build();
        studentArray[3] = builder.name("Tagir").averageGrade(0.98).recordBookNumber(366663).build();
        studentArray[4] = builder.name("Kirill").averageGrade(1.00).recordBookNumber(414144).build();
        studentArray[5] = builder.name("Kirill").averageGrade(1.00).recordBookNumber(414144).build();

        // Пример заполнения коллекции из стрима:
        Arrays.stream(studentArray).forEach(studentList::add);
    }

    /**
     * Обработчик для записи данных из коллекции в файл.
     */
    static void write() {
        // реализовать паттерн "Стратегия" по введённому коду стратегии, добавить валидацию этого кода.
        System.out.println("массив записан");
    }

    /**
     * Определяет количество вхождений конкретного студента в коллекции.
     * Делит коллекцию на {@link #NUM_THREADS} частей и обходит их в параллельных потоках.
     *
     * @param student     студент, которого будем искать
     * @param studentList лист в котором будем искать
     * @return
     */
    static int count(final Student student, final List<Student> studentList) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Integer>> futures = new LinkedList<>();

        int bucketSize = studentList.size() / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            final int leftBound = i * bucketSize;
            final int rightBound = (i == NUM_THREADS - 1) ? studentList.size() : (i + 1) * bucketSize;
            Callable<Integer> task = () -> {
                int localCount = 0;
                for (int j = leftBound; j < rightBound; j++) {
                    if (studentList.get(j).equals(student)) {
                        localCount++;
                    }
                }
                return localCount;
            };
            futures.add(executor.submit(task));
        }

        int totalCount = 0;
        for (Future<Integer> future : futures) {
            try {
                totalCount += future.get(); // Собираем результаты
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return totalCount;
    }

    /**
     * Обработчик для сортировки коллекции студентов.
     *
     * @param sortCode Код стратегии по которой будет проводиться сортировка.
     */
    static void sort(final String sortCode, final List<Student> studentList) {
        if (!handleSortCode(sortCode)) return;

        var context = new SortingContext<>(getStrategyBySortCode(sortCode));

        System.out.println("Unsorted list");
        System.out.println(studentList);
        System.out.println("-------------------------");

        context.sort(studentList);

        System.out.println("Sorted list");
        System.out.println(studentList);
    }

    /**
     * Получение стратегии сортировки по коду сортировки
     *
     * @param sortCode код сортировки
     */
    static SortStrategy<Student> getStrategyBySortCode(String sortCode) {
        return switch (sortCode) {
            case "1" -> new StudentQuickSortByName();
            case "2" -> new StudentQuickSortByAverageGrade();
            case "3" -> new StudentQuickSortByRecordBookNumber();
            default -> new AdditionalStudentQuickSortByRecordBookNumber();
        };
    }

    /**
     * Проверка корректности введенного кода сортировки
     *
     * @param sortCode код стратегии сортировки
     */
    static boolean handleSortCode(String sortCode) {
        return switch (sortCode) {
            case "1", "2", "3", "4" -> true;
            case "0" -> false;
            default -> {
                System.out.println("Wrong action code. Choose 1-4 or 0.");
                yield false;
            }
        };
    }
}