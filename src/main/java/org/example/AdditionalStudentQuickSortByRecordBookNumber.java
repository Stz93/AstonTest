package org.example;

public class AdditionalStudentQuickSortByRecordBookNumber extends AdditionalQuickSortStrategy<Student> {

    @Override
    protected int getNumericField(Student object) {
        return object.recordBookNumber;
    }

    @Override
    protected int compare(Student first, Student second) {
        return first.recordBookNumber - second.recordBookNumber;
    }
}