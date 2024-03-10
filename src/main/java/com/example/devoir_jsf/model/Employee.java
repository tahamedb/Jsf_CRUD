package com.example.devoir_jsf.model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String email;
    private Date dob;
    private String department;
    private boolean editable;

    public Employee(int id, String name, String email, Date dob, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.department = department;
    }
    public Employee(String name, String email, Date dob, String department) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.department = department;
    }
    public Employee() {
    }

    // Getters and setters

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", department='" + department + '\'' +
                ", editable=" + editable +
                '}';
    }
}
