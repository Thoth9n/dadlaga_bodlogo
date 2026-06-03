package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txt_p_ap;

    @FXML
    private Label txt_hariu;

    @FXML
    private TextField txt_n;

    @FXML
    private TextField txt_q_aq;

    @FXML
    void onAction(ActionEvent event) {
        String[] first = txt_p_ap.getText().trim().split("\\s+");
        String[] second = txt_q_aq.getText().trim().split("\\s+");

        long p = Long.parseLong(first[0]);
        long ap = Long.parseLong(first[1]);

        long q = Long.parseLong(second[0]);
        long aq = Long.parseLong(second[1]);

        long n = Long.parseLong(txt_n.getText().trim());

        long d = (aq - ap) / (q - p);
        long hariu = ap + (n - p) * d;

        txt_hariu.setText(String.valueOf(hariu));
    }

}
