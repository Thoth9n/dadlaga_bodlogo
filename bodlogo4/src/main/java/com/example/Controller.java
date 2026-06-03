package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label lbl_talbai;

    @FXML
    private Label lbl_urt;

    @FXML
    private TextField txt_r;

    @FXML
    void onAction(ActionEvent event) {
        double r = Double.parseDouble(txt_r.getText());

        double pi = 3.141592;

        double talbai = pi * r * r;
        double urt = 2 * pi * r;

        lbl_talbai.setText(String.format("%.1f", talbai));
        lbl_urt.setText(String.format("%.1f", urt));
    }

}
