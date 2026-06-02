package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField txt_sek;

    @FXML
    private Label lbl_min;

    @FXML
    private Label lbl_sek;

    @FXML
    private void onAction() {
        int sek = Integer.parseInt(txt_sek.getText());
        int min = sek / 60;
        int sec = sek % 60;

        lbl_min.setText(String.valueOf(min));
        lbl_sek.setText(String.valueOf(sec));
    }

}
