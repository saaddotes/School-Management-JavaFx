package com.example.schoolManagement;

import com.example.schoolManagement.database.TeacherDAO;
import com.example.schoolManagement.models.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TeacherDetailsController {

    @FXML
    private Label teacherIDLabel;
    @FXML
    private TextField teacherNameField;
    @FXML
    private TextField teacherGenderField;
    @FXML
    private TextField teacherSubjectField;
    @FXML
    private TextField teacherClassField;
    @FXML
    private TextField teacherEmailField;
    @FXML
    private TextField teacherPhoneField;
    @FXML
    private ImageView teacherImageView;
    @FXML
    private Button editButton;
    @FXML
    private Button updateButton;

    private Teacher teacher;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        teacherIDLabel.setText(String.valueOf(teacher.getId()));
        teacherNameField.setText(teacher.getName());
        teacherGenderField.setText(teacher.getGender());
        teacherSubjectField.setText(teacher.getSubject());
        teacherClassField.setText(teacher.getClassLevel());
        teacherEmailField.setText(teacher.getEmail());
        teacherPhoneField.setText(teacher.getPhone());
        // teacherImageView.setImage(new Image(teacher.getImagePath())); // Uncomment if image path is provided

        setFieldsEditable(false);
    }

    @FXML
    private void handleEditButtonAction() {
        // Enable text fields for editing
        setFieldsEditable(true);

        // Enable the Update button and disable the Edit button
        updateButton.setDisable(false);
        editButton.setDisable(true);
    }

    @FXML
    private void handleUpdateButtonAction() {
        // Set the new values to the teacher object
        teacher.setName(teacherNameField.getText());
        teacher.setGender(teacherGenderField.getText());
        teacher.setSubject(teacherSubjectField.getText());
        teacher.setClassLevel(teacherClassField.getText());
        teacher.setEmail(teacherEmailField.getText());
        teacher.setPhone(teacherPhoneField.getText());

        // Update the teacher in the database
        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.updateTeacher(teacher);

        // Disable text fields after updating
        setFieldsEditable(false);

        // Enable the Edit button and disable the Update button
        editButton.setDisable(false);
        updateButton.setDisable(true);
    }

    private void setFieldsEditable(boolean editable) {
        teacherNameField.setEditable(editable);
        teacherGenderField.setEditable(editable);
        teacherSubjectField.setEditable(editable);
        teacherClassField.setEditable(editable);
        teacherEmailField.setEditable(editable);
        teacherPhoneField.setEditable(editable);
    }
}
