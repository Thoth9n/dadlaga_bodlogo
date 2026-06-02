package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txt_irmeg;

    @FXML
    private Label txt_gadarguin_talbai;

    @FXML
    private Label txt_ezelhuun;

    @FXML
    private void onAction() {
        double a = Double.parseDouble(txt_irmeg.getText());

        double ezelhuun = Math.pow(a, 3);
        double gadarguinTalbai = 6 * Math.pow(a, 2);

        txt_ezelhuun.setText(String.valueOf(ezelhuun));
        txt_gadarguin_talbai.setText(String.valueOf(gadarguinTalbai));
    }
}