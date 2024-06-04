package org.example.module01.lesson47;

import java.util.Objects;

public class Student {

    private Long id;
    private String name;
    private String surname;
    private Integer age;

    public Student(Long id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student students = (Student) object;
        return Objects.equals(id, students.id) && Objects.equals(name, students.name) && Objects.equals(surname, students.surname) && Objects.equals(age, students.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }

    @Override
    public String toString() {
        return "Students{id=%d, name='%s', surname='%s', age=%d}".formatted(id, name, surname, age);
    }

}