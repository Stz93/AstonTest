package org.example;

public class AdditionalStudentQuickSortByAverageGrade extends AdditionalQuickSortStrategy<Student> {

    @Override
    protected int getNumericField(Student object) {
        return (int)(object.averageGrade);
    }

    @Override
    protected int compare(Student first, Student second) {
        return (int)(first.averageGrade - second.averageGrade);
    }
}
