package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lbl_tom;

    @FXML
    private TextField txt_input;

    @FXML
    void onAction(ActionEvent event) {
        String currentText = txt_input.getText();

        if (currentText.length() == 1 && currentText.charAt(0) >= 'a' && currentText.charAt(0) <= 'z') {
            lbl_tom.setText(currentText.toUpperCase());
            return;
        }
    }

}
