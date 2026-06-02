package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txt_too;

    @FXML
    private Label lbl_tsifr;

    @FXML
    private void onAction() {
        int a = Integer.parseInt(txt_too.getText());
        int digit = Math.abs(a / 10) % 10;
        lbl_tsifr.setText(String.valueOf(digit));
    }
}