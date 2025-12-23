package org.example;

public class StudentQuickSortByAverageGrade extends QuickSortStrategy<Student> {

    @Override
    protected int compare(Student first, Student second) {
        return (int)(first.averageGrade - second.averageGrade);
    }
}
