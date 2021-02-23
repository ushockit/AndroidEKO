package com.example.myapplication.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "people")
public class PersonModel {

    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private Long id;

    @Getter
    @Setter
    @DatabaseField
    private String firstName;

    @Getter
    @Setter
    @DatabaseField
    private String lastName;

    @Getter
    @Setter
    @DatabaseField
    private int age;

    public PersonModel() {
    }
}