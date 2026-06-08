package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lbl_hariu;

    @FXML
    private TextField txt_k;

    @FXML
    void onAction(ActionEvent event) {
        long k = Long.parseLong(txt_k.getText());

        long hariu = (k + 3) * (k + 2) * (k + 1) / 6;

        lbl_hariu.setText(String.valueOf(hariu));
    }

}
