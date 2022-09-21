package ru.job4j.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String tin;
    private LocalDateTime employmentDate;
    private List<Person> accounts;

    public static Employee of(int id, String firstName, String lastName, String tin, LocalDateTime employmentDate,
                              List<Person> accounts) {
        Employee employee = new Employee();
        employee.id = id;
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.tin = tin;
        employee.employmentDate = employmentDate;
        employee.accounts = accounts;
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public LocalDateTime getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Person> accounts) {
        this.accounts = accounts;
    }
}
