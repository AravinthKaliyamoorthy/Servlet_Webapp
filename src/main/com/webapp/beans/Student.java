package com.webapp.beans;

import lombok.Data;

@Data
public class Student {
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String studentPassword;

    public Student(int studentId, String studentName, String studentEmail, String studentPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
    }
}
