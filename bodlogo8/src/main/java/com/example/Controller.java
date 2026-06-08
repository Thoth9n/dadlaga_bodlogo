package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lbl_hariu;

    @FXML
    private TextField txt_too;

    @FXML
    void onAction(ActionEvent event) {
        String input = txt_too.getText();

        String[] parts = input.split("\\s+");

        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        int ih = Math.max(a, b);

        lbl_hariu.setText(String.valueOf(ih));
    }

}