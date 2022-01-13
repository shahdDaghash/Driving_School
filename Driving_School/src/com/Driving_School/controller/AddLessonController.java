package com.Driving_School.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddLessonController {

    @FXML
    private TextField lessonDate;

    @FXML
    private TextField student;

    @FXML
    private TextField vehicle;

    @FXML
    void addLesson(ActionEvent event) {

    }

    @FXML
    void chooseStudent(ActionEvent event) {

    }

    @FXML
    void chooseVehicle(ActionEvent event) {

    }

    @FXML
    void clearFileds(ActionEvent event) {
    	lessonDate.clear();
    	student.clear();
    	vehicle.clear();
    }

}
