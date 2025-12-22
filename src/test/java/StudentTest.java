import org.example.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void testEquals1() {
        Student student1 = new Student("testName", 0.97, 012345);

        // Корректная копия
        Student student2 = new Student("testName", 0.97, 012345);
        Assertions.assertEquals(student1, student2);
    }

    @Test
    void testEquals2() {
        Student student1 = new Student("testName", 0.97, 012345);

        // Русскаие буквы
        Student student2 = new Student("tеstNamе", 0.97, 012345);
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    void testEquals3() {
        Student student1 = new Student("testName", 0.97, 012345);

        // Регистр
        Student student2 = new Student("TESTnAME", 0.97, 012345);
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    void testEquals4() {
        Student student1 = new Student("testName", 0.97, 012345);

        // Средний балл
        Student student2 = new Student("testName", 0.79, 012345);
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    void testEquals5() {
        Student student1 = new Student("testName", 0.97, 012345);

        // Зачётка
        Student student2 = new Student("testName", 0.97, 543210);
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    void testBuilder1() {
        Student.Builder builder = new Student.Builder();
        Student student1 = new Student("testName", 0.97, 012345);
        Student student2 = builder.name("testName").averageGrade(0.97).recordBookNumber(012345).build();
        Assertions.assertEquals(student1, student2);
    }

    @Test
    void testBuilder2() {
        Student.Builder builder = new Student.Builder();
        // По умолчанию
        Student student1 = new Student("", 0.0, 0);
        Student student2 = builder.build();
        Assertions.assertEquals(student1, student2);
    }
}