package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lbl_hariu;

    @FXML
    private TextField txt_n;

    @FXML
    void onAction(ActionEvent event) {
        try {
            int n = Integer.parseInt(txt_n.getText());

            if (n > 0 && n % 2 == 1) {
                int k = (n + 1) / 2;
                int sum = k * k;
                lbl_hariu.setText(String.valueOf(sum));
            } else {
                lbl_hariu.setText("aldaa");
            }
        } catch (NumberFormatException e) {
            lbl_hariu.setText("aldaa");
        }
    }

}