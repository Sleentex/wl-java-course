package com.kmorarash.hwStream;

import java.util.*;
import java.util.stream.Collectors;

// Define the Student class
class Student {
    String name;
    int age;
    List<Grade> grades;
    String school;
    Address address;

    public Student(String name, int age, List<Grade> grades, String school, Address address) {
        this.name = name;
        this.age = age;
        this.grades = grades;
        this.school = school;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getSchool() {
        return school;
    }

    public Address getAddress() {
        return address;
    }
}

// Define the Grade class
class Grade {
    String subject;
    double score;

    public Grade(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }
}

// Define the Address class
class Address {
    String city;
    String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}

// Define the StudentGrade record
record StudentGrade(String studentName, String school, String subject, double score) {}

public class Main {
    public static void main(String[] args) {
        // Create test data
        List<Student> students = Arrays.asList(
                new Student("Alice", 16, Arrays.asList(
                        new Grade("Math", 95.0),
                        new Grade("Science", 90.0)
                ), "High School A", new Address("New York", "5th Avenue")),

                new Student("Bob", 14, Arrays.asList(
                        new Grade("Math", 85.0),
                        new Grade("Science", 80.0)
                ), "High School B", new Address("New York", "6th Avenue")),

                new Student("Charlie", 17, Arrays.asList(
                        new Grade("Math", 89.0),
                        new Grade("Science", 92.0)
                ), "High School C", new Address("New York", "7th Avenue")),

                new Student("David", 16, Arrays.asList(
                        new Grade("Math", 88.0),
                        new Grade("Science", 87.0)
                ), "High School D", new Address("Los Angeles", "8th Avenue"))
        );

        // Process the students
        List<StudentGrade> topGrades = students.stream()
                // Filter students older than 15 and living in New York
                .filter(student -> student.getAge() > 15 &&
                        "New York".equals(student.getAddress().getCity()))
                // Map grades to StudentGrade records
                .flatMap(student -> student.getGrades().stream()
                        .map(grade -> new StudentGrade(
                                student.getName(),
                                student.getSchool(),
                                grade.getSubject(),
                                grade.getScore()
                        )))
                // Sort by score in descending order
                .sorted(Comparator.comparingDouble(StudentGrade::score).reversed())
                // Limit to top 3 grades
                .limit(3)
                // Collect results into a list
                .collect(Collectors.toList());

        // Output the top grades
        topGrades.forEach(grade ->
                System.out.println("Student: " + grade.studentName() +
                        ", School: " + grade.school() +
                        ", Subject: " + grade.subject() +
                        ", Score: " + grade.score())
        );
    }
}
