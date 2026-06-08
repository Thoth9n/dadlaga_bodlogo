package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ComboBox<Integer> cmb_dun;

    @FXML
    private Label lbl_hariu;

    @FXML
    void initialize() { 
        cmb_dun.getItems().addAll(2, 3, 4, 5);
    }

    @FXML
    void onAction(ActionEvent event) {
            int dun = cmb_dun.getValue();

            if (dun > 3) {
                lbl_hariu.setText("Tentssen");
            } else {
                lbl_hariu.setText("Unasan");
            }
    }
}
