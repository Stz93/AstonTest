package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileInputStrategyTest {

    public static void main(String[] args) {
        System.out.println("=== Тестирование FileInputStrategy ===");

        // Создаем тестовые файлы
        createTestFiles();

        try {
            testConstructor();
            testLoadDataWithValidFile();
            testLoadDataWithInvalidFormat();
            testLoadDataWithInvalidData();
            testLoadDataWithEmptyFile();
            testLoadDataWithNonexistentFile();
            testLoadDataWithBuilderValidation();

            System.out.println("\n=== Все тесты пройдены ===");
        } finally {
            // Удаляем тестовые файлы
            deleteTestFiles();
        }
    }

    // 1. Создание тестовых файлов
    private static void createTestFiles() {
        try {
            // Валидный файл
            FileWriter writer = new FileWriter("test_students_valid.txt");
            writer.write("Иван,4.5,123456\n");
            writer.write("Мария,4.7,234567\n");
            writer.write("Алексей,3.8,345678\n");
            writer.close();

            // Файл с некорректным форматом
            writer = new FileWriter("test_students_invalid_format.txt");
            writer.write("Иван,4.5\n");
            writer.write("Мария\n");
            writer.write("Алексей,3.8,345678,лишнее\n");
            writer.close();

            // Файл с некорректными данными
            writer = new FileWriter("test_students_invalid_data.txt");
            writer.write(",4.5,123456\n");
            writer.write("Мария,не_число,234567\n");
            writer.write("Алексей,3.8,не_число\n");
            writer.close();

            // Пустой файл
            writer = new FileWriter("test_students_empty.txt");
            writer.write("");
            writer.close();

        } catch (IOException e) {
            System.out.println("Ошибка создания тестовых файлов: " + e.getMessage());
        }
    }

    // 2. Удаление тестовых файлов
    private static void deleteTestFiles() {
        String[] files = {
                "test_students_valid.txt",
                "test_students_invalid_format.txt",
                "test_students_invalid_data.txt",
                "test_students_empty.txt"
        };

        for (String fileName : files) {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    // 3. Тест конструктора
    private static void testConstructor() {
        System.out.println("\n1. Тест конструктора:");

        try {
            FileInputStrategy strategy = new FileInputStrategy("test_students_valid.txt");
            System.out.println("  ✓ Создание стратегии успешно");
        } catch (Exception e) {
            System.out.println("  ❌ Неожиданное исключение: " + e.getMessage());
        }

        try {
            FileInputStrategy strategy = new FileInputStrategy(null);
            System.out.println("  ❌ Ожидалось исключение для null пути");
        } catch (Exception e) {
            System.out.println("  ✓ Исключение поймано для null пути");
        }
    }

    // 4. Тест с валидным файлом
    private static void testLoadDataWithValidFile() {
        System.out.println("\n2. Тест с валидным файлом:");

        FileInputStrategy strategy = new FileInputStrategy("test_students_valid.txt");

        try {
            CustomArrayList<Student> students = strategy.loadData();

            // Проверка количества
            if (students.size() != 3) {
                System.out.println("  ❌ Ожидалось 3 студента, получено: " + students.size());
                return;
            }

            // Проверка данных первого студента
            Student first = students.get(0);
            if (!"Иван".equals(first.name)) {
                System.out.println("  ❌ Неверное имя первого студента: " + first.name);
            } else if (first.averageGrade != 4.5) {
                System.out.println("  ❌ Неверный балл первого студента: " + first.averageGrade);
            } else if (first.recordBookNumber != 123456) {
                System.out.println("  ❌ Неверный номер зачетки первого студента: " + first.recordBookNumber);
            } else {
                System.out.println("  ✓ Первый студент корректный: " + first.name + ", " +
                        first.averageGrade + ", " + first.recordBookNumber);
            }

            // Проверка данных второго студента
            Student second = students.get(1);
            if (!"Мария".equals(second.name)) {
                System.out.println("  ❌ Неверное имя второго студента: " + second.name);
            } else {
                System.out.println("  ✓ Второй студент корректный: " + second.name + ", " +
                        second.averageGrade + ", " + second.recordBookNumber);
            }

            // Проверка данных третьего студента
            Student third = students.get(2);
            if (!"Алексей".equals(third.name)) {
                System.out.println("  ❌ Неверное имя третьего студента: " + third.name);
            } else {
                System.out.println("  ✓ Третий студент корректный: " + third.name + ", " +
                        third.averageGrade + ", " + third.recordBookNumber);
            }

        } catch (Exception e) {
            System.out.println("  ❌ Неожиданное исключение: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 5. Тест с файлом некорректного формата
    private static void testLoadDataWithInvalidFormat() {
        System.out.println("\n3. Тест с файлом некорректного формата:");

        FileInputStrategy strategy = new FileInputStrategy("test_students_invalid_format.txt");

        try {
            CustomArrayList<Student> students = strategy.loadData();
            System.out.println("  ⚠ Метод выполнился без исключения, но данные могут быть некорректными");
            System.out.println("  Загружено студентов: " + students.size());

        } catch (Exception e) {
            System.out.println("  ✓ Исключение при некорректном формате: " + e.getClass().getSimpleName());
            System.out.println("  Сообщение: " + e.getMessage());
        }
    }

    // 6. Тест с файлом некорректных данных
    private static void testLoadDataWithInvalidData() {
        System.out.println("\n4. Тест с файлом некорректных данных:");

        FileInputStrategy strategy = new FileInputStrategy("test_students_invalid_data.txt");

        try {
            CustomArrayList<Student> students = strategy.loadData();
            System.out.println("  ❌ Ожидалось исключение при некорректных данных");

        } catch (NumberFormatException e) {
            System.out.println("  ✓ NumberFormatException при некорректных числах");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ IllegalArgumentException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ⚠ Другое исключение: " + e.getClass().getSimpleName());
        }
    }

    // 7. Тест с пустым файлом
    private static void testLoadDataWithEmptyFile() {
        System.out.println("\n5. Тест с пустым файлом:");

        FileInputStrategy strategy = new FileInputStrategy("test_students_empty.txt");

        try {
            CustomArrayList<Student> students = strategy.loadData();

            if (students.size() == 0) {
                System.out.println("  ✓ Загружено 0 студентов из пустого файла");
            } else {
                System.out.println("  ❌ Ожидалось 0 студентов, получено: " + students.size());
            }

        } catch (Exception e) {
            System.out.println("  ❌ Неожиданное исключение: " + e.getMessage());
        }
    }

    // 8. Тест с несуществующим файлом
    private static void testLoadDataWithNonexistentFile() {
        System.out.println("\n6. Тест с несуществующим файлом:");

        FileInputStrategy strategy = new FileInputStrategy("несуществующий_файл.txt");

        try {
            CustomArrayList<Student> students = strategy.loadData();
            System.out.println("  ❌ Ожидалось исключение для несуществующего файла");

        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                System.out.println("  ✓ RuntimeException с IOException внутри");
            } else {
                System.out.println("  ⚠ RuntimeException, но не с IOException: " + e.getCause());
            }
        } catch (Exception e) {
            System.out.println("  ⚠ Другое исключение: " + e.getClass().getSimpleName());
        }
    }

    // 9. Тест валидации Builder
    private static void testLoadDataWithBuilderValidation() {
        System.out.println("\n7. Тест валидации Builder:");

        try {
            // Создаем временный файл с некорректными данными для Builder
            FileWriter writer = new FileWriter("test_builder_validation.txt");
            writer.write(" ,-1.0,-100\n"); // Пустое имя, отрицательный балл, отрицательный номер
            writer.close();

            FileInputStrategy strategy = new FileInputStrategy("test_builder_validation.txt");

            try {
                CustomArrayList<Student> students = strategy.loadData();
                System.out.println("  ⚠ Builder не проверил данные (пустое имя, отрицательные значения)");
            } catch (IllegalArgumentException e) {
                System.out.println("  ✓ Builder выбросил IllegalArgumentException: " + e.getMessage());
            }

            // Удаляем временный файл
            new File("test_builder_validation.txt").delete();

        } catch (IOException e) {
            System.out.println("  ❌ Ошибка создания тестового файла: " + e.getMessage());
        }
    }
}
