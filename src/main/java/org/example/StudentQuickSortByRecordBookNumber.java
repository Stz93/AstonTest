package org.example;

public class StudentQuickSortByRecordBookNumber extends QuickSortStrategy<Student> {

    @Override
    protected int compare(Student first, Student second) {
        return first.recordBookNumber - second.recordBookNumber;
    }
}