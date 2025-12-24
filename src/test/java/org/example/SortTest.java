package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SortTest {

    final static Student.Builder builder = new Student.Builder();

    final static Student student1 = builder.name("Alex").averageGrade(0.91).recordBookNumber(100001).build();
    final static Student student2 = builder.name("Bob").averageGrade(0.92).recordBookNumber(100002).build();
    final static Student student3 = builder.name("Chloe").averageGrade(0.93).recordBookNumber(100003).build();
    final static Student student4 = builder.name("Daniel").averageGrade(0.94).recordBookNumber(100004).build();
    final static Student student5 = builder.name("Emily").averageGrade(0.95).recordBookNumber(100005).build();
    final static Student student6 = builder.name("Freya").averageGrade(0.96).recordBookNumber(100006).build();
    final static Student student7 = builder.name("George").averageGrade(0.97).recordBookNumber(100007).build();

    static CustomArrayList<Student> notSortedList = new CustomArrayList<>();

    static {
        notSortedList.add(student1);
        notSortedList.add(student7);
        notSortedList.add(student4);
        notSortedList.add(student2);
        notSortedList.add(student5);
        notSortedList.add(student3);
        notSortedList.add(student6);
    }

    @Test
    void testSort1() {
        CustomArrayList<Student> actualList = new CustomArrayList<>();
        actualList.addAll(notSortedList);
        actualList.print();
        System.out.println("-----------------------------");
        Main.sort("1", actualList);
        actualList.print();

        //actualList = actualList.stream().sorted((a, b) -> a.name.compareTo(b.name)).toList();

        CustomArrayList<Student> expectedList = new CustomArrayList<>();
        expectedList.add(student1);
        expectedList.add(student2);
        expectedList.add(student3);
        expectedList.add(student4);
        expectedList.add(student5);
        expectedList.add(student6);
        expectedList.add(student7);

        for (int i = 0; i < actualList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), actualList.get(i));
        }
    }

    @Test
    void testSort2() {
        List<Student> actualList = new CustomArrayList<>();
        actualList.addAll(notSortedList);
        Main.sort("2", actualList);

        CustomArrayList<Student> expectedList = new CustomArrayList<>();
        expectedList.add(student1);
        expectedList.add(student2);
        expectedList.add(student3);
        expectedList.add(student4);
        expectedList.add(student5);
        expectedList.add(student6);
        expectedList.add(student7);

        for (int i = 0; i < actualList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), actualList.get(i));
        }
    }

    @Test
    void testSort3() {
        List<Student> actualList = new CustomArrayList<>();
        actualList.addAll(notSortedList);
        Main.sort("3", actualList);

        CustomArrayList<Student> expectedList = new CustomArrayList<>();
        expectedList.add(student1);
        expectedList.add(student2);
        expectedList.add(student3);
        expectedList.add(student4);
        expectedList.add(student5);
        expectedList.add(student6);
        expectedList.add(student7);

        for (int i = 0; i < actualList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), actualList.get(i));
        }
    }

    @Test
    void testSort4() {
        List<Student> actualList = new CustomArrayList<>();
        actualList.addAll(notSortedList);
        Main.sort("4", actualList);

        CustomArrayList<Student> expectedList = new CustomArrayList<>();
        expectedList.add(student1);
        expectedList.add(student7);
        expectedList.add(student2);
        expectedList.add(student4);
        expectedList.add(student5);
        expectedList.add(student3);
        expectedList.add(student6);

        for (int i = 0; i < actualList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), actualList.get(i));
        }
    }
}