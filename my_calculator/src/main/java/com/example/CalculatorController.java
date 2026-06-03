package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label display;

    private double storedValue = 0;
    private String pendingOperator = "";
    private boolean startNewNumber = true;

    @FXML
    void onButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = String.valueOf(button.getUserData());

        if (value.matches("\\d")) {
            inputDigit(value);
        } else if (".".equals(value)) {
            inputDot();
        } else if ("C".equals(value)) {
            clear();
        } else if ("backspace".equals(value)) {
            backspace();
        } else if ("+/-".equals(value)) {
            toggleSign();
        } else if ("sin".equals(value) || "cos".equals(value) || "tan".equals(value)) {
            applyFunction(value);
        } else if ("=".equals(value)) {
            calculate();
            pendingOperator = "";
        } else {
            chooseOperator(value);
        }
    }

    private void inputDigit(String digit) {
        if (startNewNumber || "0".equals(display.getText())) {
            setDisplay(digit);
            startNewNumber = false;
        } else {
            setDisplay(display.getText() + digit);
        }
    }

    private void inputDot() {
        if (startNewNumber) {
            setDisplay("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            setDisplay(display.getText() + ".");
        }
    }

    private void chooseOperator(String operator) {
        calculate();
        storedValue = currentValue();
        pendingOperator = operator;
        startNewNumber = true;
    }

    private void calculate() {
        if (pendingOperator.isEmpty()) {
            return;
        }

        double current = currentValue();
        double result;

        switch (pendingOperator) {
            case "+":
                result = storedValue + current;
                break;
            case "-":
                result = storedValue - current;
                break;
            case "*":
                result = storedValue * current;
                break;
            case "/":
                if (current == 0) {
                    setDisplay("Error");
                    startNewNumber = true;
                    return;
                }
                result = storedValue / current;
                break;
            default:
                return;
        }

        setDisplay(format(result));
        startNewNumber = true;
    }

    private void applyFunction(String function) {
        double radians = Math.toRadians(currentValue());
        double result;

        switch (function) {
            case "sin":
                result = Math.sin(radians);
                break;
            case "cos":
                result = Math.cos(radians);
                break;
            case "tan":
                result = Math.tan(radians);
                break;
            default:
                return;
        }

        setDisplay(format(result));
        startNewNumber = true;
    }

    private void toggleSign() {
        if ("0".equals(display.getText()) || "Error".equals(display.getText())) {
            return;
        }

        if (display.getText().startsWith("-")) {
            setDisplay(display.getText().substring(1));
        } else {
            setDisplay("-" + display.getText());
        }
    }

    private void clear() {
        setDisplay("0");
        storedValue = 0;
        pendingOperator = "";
        startNewNumber = true;
    }

    private void backspace() {
        if (startNewNumber || "Error".equals(display.getText())) {
            setDisplay("0");
            startNewNumber = true;
            return;
        }

        String text = display.getText();
        if (text.length() <= 1 || (text.length() == 2 && text.startsWith("-"))) {
            setDisplay("0");
            startNewNumber = true;
        } else {
            setDisplay(text.substring(0, text.length() - 1));
        }
    }

    private void setDisplay(String text) {
        display.setText(text);

        if (text.length() > 16) {
            display.setStyle("-fx-font-size: 22px;");
        } else if (text.length() > 11) {
            display.setStyle("-fx-font-size: 27px;");
        } else {
            display.setStyle("");
        }
    }

    private double currentValue() {
        if ("Error".equals(display.getText())) {
            return 0;
        }
        return Double.parseDouble(display.getText());
    }

    private String format(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.0000000001) {
            return String.valueOf((long) Math.rint(value));
        }
        return String.valueOf(value);
    }
}
