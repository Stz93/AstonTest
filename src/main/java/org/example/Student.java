package org.example;

public class Student {
    final String name;
    final double averageGrade;
    final int recordBookNumber;

    public Student(final String name, final double averageGrade, final int recordBookNumber) {
        this.name = name;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }

    /**
     * Билдер для класса {@link Student}.
     */
    public static class Builder {
        private String name = "";
        private double averageGrade = 0.0;
        private int recordBookNumber = 0;


        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder averageGrade(final double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder recordBookNumber(final int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(name, averageGrade, recordBookNumber);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        Student student = (Student) obj;
        return name.equals(student.name) && Math.abs(averageGrade - student.averageGrade) < 0.001 && recordBookNumber == student.recordBookNumber;
    }

    @Override
    public String toString() {
        return "\t name : " + name + " \taverageGrade : " + averageGrade + " \trecordBookNumber : " + recordBookNumber;
    }
}
