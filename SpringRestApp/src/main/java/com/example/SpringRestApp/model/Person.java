package com.example.SpringRestApp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@NamedQuery(name="Person.MethodNamefindById",query ="SELECT p from Person p where p.id=?1")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "person_sequence")
    @SequenceGenerator(name="person_sequence",
            sequenceName = "person_sequence",allocationSize = 1) //provide an id generator

    private  Long id;
    private String name;
    private LocalDate dob;
    private String email;
    @Transient
    private Integer age;

    public Person(){

    }

    public Person(Long id, String name,  String email,LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Person(String name,  String email,LocalDate dob) {
        this.name = name;
        this.dob = dob;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
