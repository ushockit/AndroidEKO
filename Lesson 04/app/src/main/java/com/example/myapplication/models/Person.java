package com.example.myapplication.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Person {
    static int counter = 0;

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birth;
    private int picture;

    public Person(String firstName, String lastName, LocalDate birth, int resourcePicture) {
        id = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.picture = resourcePicture;
    }


}
