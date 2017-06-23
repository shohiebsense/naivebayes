package com.shohiebsense.myapplication.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shohiebsense on 23/06/17.
 */

public class Human extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private double height;
    private String gender;
    private String classification;


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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
