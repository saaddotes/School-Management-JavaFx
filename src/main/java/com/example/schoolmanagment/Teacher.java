package com.example.schoolmanagment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Teacher {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty subject;
    private final SimpleIntegerProperty classLevel;
    private final SimpleStringProperty section;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;

    public Teacher(int id, String name, String gender, String subject, int classLevel, String section, String email, String phone) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.subject = new SimpleStringProperty(subject);
        this.classLevel = new SimpleIntegerProperty(classLevel);
        this.section = new SimpleStringProperty(section);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getSubject() {
        return subject.get();
    }

    public int getClassLevel() {
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
}
