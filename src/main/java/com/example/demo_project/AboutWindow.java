package com.example.demo_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutWindow implements Initializable {

    @FXML
    private ChoiceBox<String> group_choice_box;

    @FXML
    private Button close_button;

    @FXML
    private Spinner<Integer> grade_spinner;

    private final String[] groups = {"АнД-21", "КН-21", "КН-22"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group_choice_box.getItems().addAll(groups);

        SpinnerValueFactory<Integer> spinValFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);

        spinValFact.setValue(5);

        grade_spinner.setValueFactory(spinValFact);
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) close_button.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
