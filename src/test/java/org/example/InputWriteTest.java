package org.example;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class InputWriteTest {
    final static Student.Builder builder = new Student.Builder();

    final static Student student1 = builder.name("Alex").averageGrade(0.91).recordBookNumber(100001).build();
    final static Student student2 = builder.name("Bob").averageGrade(0.92).recordBookNumber(100002).build();
    final static Student student3 = builder.name("Chloe").averageGrade(0.93).recordBookNumber(100003).build();
    final static Student student4 = builder.name("Daniel").averageGrade(0.94).recordBookNumber(100004).build();
    final static Student student5 = builder.name("Emily").averageGrade(0.95).recordBookNumber(100005).build();
    final static Student student6 = builder.name("Freya").averageGrade(0.96).recordBookNumber(100006).build();
    final static Student student7 = builder.name("George").averageGrade(0.97).recordBookNumber(100007).build();

    static CustomArrayList<Student> studentsExpected = new CustomArrayList<>();

    static {
        studentsExpected.add(student1);
        studentsExpected.add(student7);
        studentsExpected.add(student4);
        studentsExpected.add(student2);
        studentsExpected.add(student5);
        studentsExpected.add(student3);
        studentsExpected.add(student6);
    }

    @Test
    void input() {
        Main.write("2", studentsExpected);
        CustomArrayList<Student> studentsActuall = new CustomArrayList<Student>();
        Main.input("1", studentsActuall);
        for (int i = 0; i < studentsExpected.size(); i++) {
            assertEquals(studentsExpected.get(i), studentsActuall.get(i));
        }
    }

}