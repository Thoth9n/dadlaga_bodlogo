package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private Button btn_result;

    @FXML
    private TextField txt_result;
    private double number1 = 0;
    private String operator = "";
    private boolean isStartNumber = true;

    @FXML
    void onClearClick(ActionEvent event) {
        txt_result.setText("0");
        number1 = 0;
        operator = "";
        isStartNumber = true;
    }

    @FXML
    void onEqualClick(ActionEvent event) {
        String currentText = txt_result.getText();
        if (currentText.isEmpty() || currentText.equals("aldaa")) {
            return;
        }

        if (hasExpression(currentText)) {
            try {
                txt_result.setText(format(new ExpressionParser(currentText).parse()));
                operator = "";
                isStartNumber = true;
            } catch (RuntimeException e) {
                txt_result.setText("aldaa");
                operator = "";
                isStartNumber = true;
            }
            return;
        }

        if (operator.isEmpty()) {
            return;
        }

        double number2 = Double.parseDouble(currentText);
        double result = 0;

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    txt_result.setText("aldaa");
                    operator = "";
                    isStartNumber = true;
                    return;
                }
                result = number1 / number2;
                break;
        }

        txt_result.setText(format(result));
        operator = "";
        isStartNumber = true;
    }

    @FXML
    void onHaaltClick(ActionEvent event) {
        String bracket = ((Button) event.getSource()).getText();
        if (isStartNumber || txt_result.getText().equals("0") || txt_result.getText().equals("aldaa")) {
            txt_result.setText(bracket);
        } else {
            txt_result.appendText(bracket);
        }
        isStartNumber = false;
    }

    @FXML
    void onNumberClick(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();

        if (isStartNumber) {
            txt_result.setText(number);
            isStartNumber = false;
        } else {
            if (number.equals(".") && txt_result.getText().contains(".")) {
                return;
            }
            else if (number.equals("0") && txt_result.getText().equals("0")) {
                return;
            }
            txt_result.appendText(number);
        }
    }

    @FXML
    void onOperatorClick(ActionEvent event) {
        String clickedOperator = ((Button) event.getSource()).getText();
        String currentText = txt_result.getText();
        if (currentText.isEmpty() || currentText.equals("aldaa")){
            return;
        }

        if (clickedOperator.equals("sin") || clickedOperator.equals("cos") || clickedOperator.equals("tan")) {
            if (isStartNumber || currentText.equals("0")) {
                txt_result.setText(clickedOperator + "(");
                isStartNumber = false;
            } else if (hasExpression(currentText)) {
                txt_result.setText(clickedOperator + "(" + currentText + ")");
            } else {
                double radians = Math.toRadians(Double.parseDouble(currentText));
                double result;
                if (clickedOperator.equals("sin")) {
                    result = Math.sin(radians);
                } else if (clickedOperator.equals("cos")) {
                    result = Math.cos(radians);
                } else {
                    result = Math.tan(radians);
                }
                txt_result.setText(format(result));
                isStartNumber = true;
            }
            return;
        }

        if (hasExpression(currentText)) {
            char last = currentText.charAt(currentText.length() - 1);
            if (isOperator(last)) {
                txt_result.setText(currentText.substring(0, currentText.length() - 1) + clickedOperator);
            } else {
                txt_result.appendText(clickedOperator);
            }
            isStartNumber = false;
            return;
        }

        number1 = Double.parseDouble(currentText);
        operator = clickedOperator;
        isStartNumber = true;
    }   

    @FXML
    void onPosNegClick(ActionEvent event) {
        String currentText = txt_result.getText();
        if (currentText.isEmpty() || currentText.equals("0") || currentText.equals("aldaa")) {
            return;
        }
        double value = Double.parseDouble(currentText);
        value = value * -1;
        if (value%1 == 0) {
            txt_result.setText(String.valueOf((long) value));
        } else {
            txt_result.setText(String.valueOf(value));
        }
    }

    @FXML
    void onBackspaceClick(ActionEvent event) {
        String currentText = txt_result.getText();
        if (currentText.isEmpty() || currentText.equals("0") || currentText.equals("aldaa")) {
            txt_result.setText("0");
            isStartNumber = true;
            return;
        }

        if (currentText.length() == 1 || (currentText.length() == 2 && currentText.startsWith("-"))) {
            txt_result.setText("0");
            isStartNumber = true;
        } else {
            txt_result.setText(currentText.substring(0, currentText.length() - 1));
            isStartNumber = false;
        }
    }

    private boolean hasExpression(String text) {
        return text.contains("+") || text.contains("*") || text.contains("/") || text.contains("(")
                || text.contains(")") || text.contains("sin") || text.contains("cos") || text.contains("tan")
                || (text.indexOf("-") > 0);
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "aldaa";
        }
        if (Math.abs(value - Math.rint(value)) < 0.0000000001) {
            return String.valueOf((long) Math.rint(value));
        }
        String result = String.valueOf(value);
        if (result.length() > 16) {
            return String.format("%.10f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
        }
        return result;
    }

    private static class ExpressionParser {
        private final String text;
        private int pos = -1;
        private int ch;

        ExpressionParser(String text) {
            this.text = text.replace(" ", "");
        }

        double parse() {
            nextChar();
            double value = parseExpression();
            if (pos < text.length()) {
                throw new RuntimeException("Unexpected: " + (char) ch);
            }
            return value;
        }

        private void nextChar() {
            pos++;
            ch = pos < text.length() ? text.charAt(pos) : -1;
        }

        private boolean eat(int charToEat) {
            while (ch == ' ') {
                nextChar();
            }
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        private double parseExpression() {
            double value = parseTerm();
            while (true) {
                if (eat('+')) {
                    value += parseTerm();
                } else if (eat('-')) {
                    value -= parseTerm();
                } else {
                    return value;
                }
            }
        }

        private double parseTerm() {
            double value = parseFactor();
            while (true) {
                if (eat('*')) {
                    value *= parseFactor();
                } else if (eat('/')) {
                    double divisor = parseFactor();
                    if (divisor == 0) {
                        throw new RuntimeException("Division by zero");
                    }
                    value /= divisor;
                } else {
                    return value;
                }
            }
        }

        private double parseFactor() {
            if (eat('+')) {
                return parseFactor();
            }
            if (eat('-')) {
                return -parseFactor();
            }

            double value;
            int startPos = this.pos;

            if (eat('(')) {
                value = parseExpression();
                if (!eat(')')) {
                    throw new RuntimeException("Missing )");
                }
            } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                while ((ch >= '0' && ch <= '9') || ch == '.') {
                    nextChar();
                }
                value = Double.parseDouble(text.substring(startPos, this.pos));
            } else if (ch >= 'a' && ch <= 'z') {
                while (ch >= 'a' && ch <= 'z') {
                    nextChar();
                }
                String function = text.substring(startPos, this.pos);
                value = parseFactor();
                double radians = Math.toRadians(value);
                if (function.equals("sin")) {
                    value = Math.sin(radians);
                } else if (function.equals("cos")) {
                    value = Math.cos(radians);
                } else if (function.equals("tan")) {
                    value = Math.tan(radians);
                } else {
                    throw new RuntimeException("Unknown function");
                }
            } else {
                throw new RuntimeException("Unexpected");
            }

            return value;
        }
    }

}
