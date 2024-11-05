package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';
    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3,
            button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide,
            buttonPercent, buttonClear, buttonOFF, buttonEqual,
            buttonSquareRoot, buttonExponential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.subtract);
        buttonDivide = findViewById(R.id.division);
        buttonDot = findViewById(R.id.decimal);
        buttonMultiply = findViewById(R.id.multiply);
        buttonClear = findViewById(R.id.clear);
        buttonOFF = findViewById(R.id.off);
        buttonEqual = findViewById(R.id.equal);
        buttonPercent = findViewById(R.id.percent);
        buttonSquareRoot = findViewById(R.id.squareRoot);
        buttonExponential = findViewById(R.id.exponential);
        button0.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "0"));
        button1.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "1"));
        button2.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "2"));
        button3.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "3"));
        button4.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "4"));
        button5.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "5"));
        button6.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "6"));
        button7.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "7"));
        button8.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "8"));
        button9.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "9"));

        buttonAdd.setOnClickListener(view -> {
            allCalculations();
            currentSymbol = ADDITION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "+");
            inputDisplay.setText(null);
        });

        buttonSub.setOnClickListener(view -> {
            allCalculations();
            currentSymbol = SUBTRACTION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "-");
            inputDisplay.setText(null);
        });

        buttonMultiply.setOnClickListener(view -> {
            allCalculations();
            currentSymbol = MULTIPLICATION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "x");
            inputDisplay.setText(null);
        });

        buttonDivide.setOnClickListener(view -> {
            allCalculations();
            currentSymbol = DIVISION;
            outputDisplay.setText(decimalFormat.format(firstValue) + "/");
            inputDisplay.setText(null);
        });

        buttonPercent.setOnClickListener(view -> {
            allCalculations();
            currentSymbol = PERCENT;
            outputDisplay.setText(decimalFormat.format(firstValue) + "%");
            inputDisplay.setText(null);
        });

        buttonSquareRoot.setOnClickListener(view -> {
            if (inputDisplay.getText().length() > 0) {
                double value = Double.parseDouble(inputDisplay.getText().toString());
                firstValue = Math.sqrt(value);
                outputDisplay.setText("√" + decimalFormat.format(firstValue));
                inputDisplay.setText("");
            }
        });

        buttonExponential.setOnClickListener(view -> {
            if (inputDisplay.getText().length() > 0) {
                double value = Double.parseDouble(inputDisplay.getText().toString());
                firstValue = Math.exp(value);
                outputDisplay.setText("exp(" + decimalFormat.format(value) + ") = " + decimalFormat.format(firstValue));
                inputDisplay.setText("");
            }
        });

        buttonDot.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "."));

        buttonClear.setOnClickListener(view -> {
            if (inputDisplay.getText().length() > 0) {
                CharSequence currentText = inputDisplay.getText();
                inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                firstValue = Double.NaN;
                secondValue = Double.NaN;
                inputDisplay.setText("");
                outputDisplay.setText("");
            }
        });

        buttonOFF.setOnClickListener(view -> finish());

        buttonEqual.setOnClickListener(view -> {
            allCalculations();
            outputDisplay.setText(decimalFormat.format(firstValue));
            firstValue = Double.NaN;
            currentSymbol = '0';
        });
    }

    private void allCalculations() {
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);

            if (currentSymbol == ADDITION)
                firstValue = this.firstValue + secondValue;
            else if (currentSymbol == SUBTRACTION)
                firstValue = this.firstValue - secondValue;
            else if (currentSymbol == MULTIPLICATION)
                firstValue = this.firstValue * secondValue;
            else if (currentSymbol == DIVISION)
                firstValue = this.firstValue / secondValue;
            else if (currentSymbol == PERCENT)
                firstValue = this.firstValue % secondValue;
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
            }
        }
    }
}
