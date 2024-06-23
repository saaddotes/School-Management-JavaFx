package com.example.schoolManagement.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Teacher {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty classLevel;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;

    public Teacher(int id, String name, String gender, String subject, String classLevel, String email, String phone) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.subject = new SimpleStringProperty(subject);
        this.classLevel = new SimpleStringProperty(classLevel);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getClassLevel() {
        return classLevel.get();
    }

    public void setClassLevel(String classLevel) {
        this.classLevel.set(classLevel);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleStringProperty classLevelProperty() {
        return classLevel;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public String getImagePath() {
        return "images/teacher.png";
    }
}
