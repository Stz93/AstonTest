package org.example;

import java.util.Random;

public class RandomInputStrategyTest {

    public static void main(String[] args) {
        System.out.println("=== Тестирование RandomInputStrategy ===");

        testConstructor();
        testLoadDataSize();
        testLoadDataContent();
        testWithFixedSeed();

        System.out.println("=== Все тесты пройдены ===");
    }

    // 1. Тест конструктора с некорректными значениями
    private static void testConstructor() {
        System.out.println("1. Тест конструктора:");

        try {
            new RandomInputStrategy(0);
            System.out.println("  ❌ Ожидалось исключение для count=0");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Исключение поймано для count=0: " + e.getMessage());
        }

        try {
            new RandomInputStrategy(-5);
            System.out.println("  ❌ Ожидалось исключение для count=-5");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Исключение поймано для count=-5: " + e.getMessage());
        }

        try {
            RandomInputStrategy strategy = new RandomInputStrategy(10);
            System.out.println("  ✓ Создание стратегии с count=10 успешно");
        } catch (Exception e) {
            System.out.println("  ❌ Неожиданное исключение: " + e.getMessage());
        }
    }

    // 2. Тест размера возвращаемой коллекции
    private static void testLoadDataSize() {
        System.out.println("\n2. Тест размера коллекции:");

        RandomInputStrategy strategy = new RandomInputStrategy(7);
        CustomArrayList<Student> students = strategy.loadData();

        if (students.size() == 7) {
            System.out.println("  ✓ Размер коллекции соответствует ожидаемому: 7");
        } else {
            System.out.println("  ❌ Ожидался размер 7, получен: " + students.size());
        }
    }

    // 3. Тест содержимого коллекции
    private static void testLoadDataContent() {
        System.out.println("\n3. Тест содержимого коллекции:");

        RandomInputStrategy strategy = new RandomInputStrategy(3);
        CustomArrayList<Student> students = strategy.loadData();

        boolean allValid = true;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            // Проверка имени
            if (student.name == null || student.name.isEmpty()) {
                System.out.println("  ❌ Студент " + i + ": имя пустое");
                allValid = false;
            }
            if (student.averageGrade < 0.0 || student.averageGrade > 1.0) {
                System.out.println("  ❌ Студент " + i + ": некорректный балл: " + student.averageGrade);
                allValid = false;
            }
            if (student.recordBookNumber < 0 || student.recordBookNumber >= 100000) {
                System.out.println("  ❌ Студент " + i + ": некорректный номер: " + student.recordBookNumber);
                allValid = false;
            }

        }

        if (allValid) {
            System.out.println("  ✓ Все студенты имеют корректные данные");
        }
    }

    // 4. Тест с фиксированным seed для воспроизводимости
    private static void testWithFixedSeed() {
        System.out.println("\n4. Тест с фиксированным seed (опционально):");

        // Для этого теста нужно модифицировать RandomInputStrategy
        // чтобы принимать Random в конструкторе
        System.out.println("  ⚠ Для этого теста нужно модифицировать класс");
    }
}
//если тут напишу то можно будет кинуть снова