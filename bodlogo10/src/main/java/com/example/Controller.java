package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ComboBox<Integer> cmb_n;

    @FXML
    private Label lbl_hariu;

    @FXML
    public void initialize() {
        cmb_n.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        lbl_hariu.setWrapText(true);
        lbl_hariu.setPrefWidth(300);
        lbl_hariu.setPrefHeight(220);
        lbl_hariu.setMinHeight(220);
        lbl_hariu.setMaxHeight(220);
    }

    @FXML
    void onAction(ActionEvent event) {
        Integer n = cmb_n.getValue();

        if (n == null) {
            lbl_hariu.setText("Тоо сонгоно уу.");
            return;
        }

        String hariu = "";

        for (int i = 1; i <= 10; i++) {
            hariu += n + "*" + i + "=" + (n * i);

            if (i < 10) {
                hariu += "\n";
            }
        }

        lbl_hariu.setText(hariu);
    }
}