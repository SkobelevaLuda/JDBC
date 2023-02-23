package model;

import java.util.Objects;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String gender;
    private int age;
    City city;

    public Employee(long id, String name, String surname, String gender, int age, City city) {

    }

    public Employee(String name, String surname, String gender, int age, City city) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Employee(String name, String surname, String gender, int age) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
    }

    public long getId() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, gender, age);
    }

    @Override
    public String toString() {
        return "Сотрудник" + id + " Имя " + name + " Фамилия " + surname + " пол" + gender + " Возраст " + age;
    }

}
