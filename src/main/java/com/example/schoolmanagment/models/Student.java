package com.example.schoolmanagment.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty name;
    private final SimpleStringProperty father;
    private final SimpleStringProperty dob;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty classLevel;
    private final SimpleStringProperty section;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleIntegerProperty rollNumber;

    public Student(String name, String father, String dob, String gender, String classLevel, String section, String email, String phone, int rollNumber) {
        this.name = new SimpleStringProperty(name);
        this.father = new SimpleStringProperty(father);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.classLevel = new SimpleStringProperty(classLevel);
        this.section = new SimpleStringProperty(section);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.rollNumber = new SimpleIntegerProperty(rollNumber);
    }

    public int getRollNumber() {
        return rollNumber.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDob() {
        return dob.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getClassLevel() {
        return classLevel.get();
    }

    public String getSection() {
        return section.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getFather() {
        return father.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty fatherProperty() {
        return father;
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public SimpleStringProperty classLevelProperty() {
        return classLevel;
    }

    public SimpleStringProperty sectionProperty() {
        return section;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public SimpleIntegerProperty rollNumberProperty() {
        return rollNumber;
    }

    public String getFatherName() {
        return father.get();
    }
}
