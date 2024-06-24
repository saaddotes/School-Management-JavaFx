package com.example.schoolManagement.models;

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
    private final SimpleStringProperty rollNumber;
    private final SimpleIntegerProperty totalFees;
    private final SimpleStringProperty feesStatus;

    public Student(String name, String father, String dob, String gender, String classLevel, String section, String email, String phone, String rollNumber, int totalFees, String feesStatus) {
        this.name = new SimpleStringProperty(name);
        this.father = new SimpleStringProperty(father);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.classLevel = new SimpleStringProperty(classLevel);
        this.section = new SimpleStringProperty(section);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.rollNumber = new SimpleStringProperty(rollNumber);
        this.totalFees = new SimpleIntegerProperty(totalFees);
        this.feesStatus = new SimpleStringProperty(feesStatus);
    }

    public String getRollNumber() {
        return rollNumber.get();
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber.set(rollNumber);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDob() {
        return dob.get();
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getClassLevel() {
        return classLevel.get();
    }

    public void setClassLevel(String classLevel) {
        this.classLevel.set(classLevel);
    }

    public String getSection() {
        return section.get();
    }

    public void setSection(String section) {
        this.section.set(section);
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

    public String getFather() {
        return father.get();
    }

    public void setFather(String father) {
        this.father.set(father);
    }

    public int getTotalFees() {
        return totalFees.get();
    }

    public void setTotalFees(int totalFees) {
        this.totalFees.set(totalFees);
    }

    public String isFeesStatus() {
        return feesStatus.get();
    }

    public void setFeesStatus(String feesStatus) {
        this.feesStatus.set(feesStatus);
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

    public SimpleStringProperty rollNumberProperty() {
        return rollNumber;
    }

    public SimpleIntegerProperty totalFeesProperty() {
        return totalFees;
    }

    public SimpleStringProperty feesStatusProperty() {
        return feesStatus;
    }
}
