package com.example.schoolManagement;

import com.example.schoolManagement.database.TeacherDAO;
import com.example.schoolManagement.models.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TeacherDetailsController {

    @FXML
    private ImageView teacherImageView;
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
    private Button editButton;

    private Teacher teacher;
    private boolean isEditing = false;

    public void initData(Teacher teacher) {
        this.teacher = teacher;
        teacherIDLabel.setText(String.valueOf(teacher.getId()));
        teacherNameField.setText(teacher.getName());
        teacherGenderField.setText(teacher.getGender());
        teacherSubjectField.setText(teacher.getSubject());
        teacherClassField.setText(teacher.getClassLevel());
        teacherEmailField.setText(teacher.getEmail());
        teacherPhoneField.setText(teacher.getPhone());
//        teacherImageView.setImage(new Image(teacher.getImagePath()));

        setFieldsEditable(false);
    }

    @FXML
    private void handleEditButtonAction() {
        if (isEditing) {
            // Update teacher details
            teacher.setName(teacherNameField.getText());
            teacher.setGender(teacherGenderField.getText());
            teacher.setSubject(teacherSubjectField.getText());
            teacher.setClassLevel(teacherClassField.getText());
            teacher.setEmail(teacherEmailField.getText());
            teacher.setPhone(teacherPhoneField.getText());

            // Update the teacher in the database
            TeacherDAO teacherDAO = new TeacherDAO();
            teacherDAO.updateTeacher(teacher);

            // Switch back to view mode
            setFieldsEditable(false);
            editButton.setText("Edit");
        } else {
            // Switch to edit mode
            setFieldsEditable(true);
            editButton.setText("Update");
        }
        isEditing = !isEditing;
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
