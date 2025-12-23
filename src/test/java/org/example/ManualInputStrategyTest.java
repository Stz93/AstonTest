package org.example;

public class ManualInputStrategyTest {

    public static void main(String[] args) {
        System.out.println("=== Упрощенный тест ManualInputStrategy ===");
        System.out.println("Этот тест требует ручного ввода данных!");

        testConstructor();

        // Не тестируем loadData, так как требует интерактивного ввода
        System.out.println("\nТест loadData требует ручного ввода.");
        System.out.println("Запустите программу и выберите ручной ввод для тестирования.");
    }

    private static void testConstructor() {
        System.out.println("\n1. Тест конструктора:");

        try {
            new ManualInputStrategy(-5);
            System.out.println("  ❌ Ожидалось исключение для count=-5");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Исключение поймано для count=-5");
        }

        try {
            new ManualInputStrategy(0);
            System.out.println("  ❌ Ожидалось исключение для count=0");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Исключение поймано для count=0");
        }

        try {
            ManualInputStrategy strategy = new ManualInputStrategy(3);
            System.out.println("  ✓ Создание стратегии с count=3 успешно");
            System.out.println("  ⚠ Для теста loadData() запустите программу и введите данные");
        } catch (Exception e) {
            System.out.println("  ❌ Неожиданное исключение: " + e.getMessage());
        }
    }
}
//если тут напишу то можно будет кинуть снова