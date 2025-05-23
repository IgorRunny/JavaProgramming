package com.example.model;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    // getters/setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}