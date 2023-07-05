package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTV, solutionTV;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose, buttonDivide;
    MaterialButton buttonMultiply, buttonPlus, buttonMinus, buttonEquals, buttonAC, buttonDot;

    private String dataToCalculate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonC = findViewById(R.id.button_c);
        buttonBracketOpen = findViewById(R.id.button_open_bracket);
        buttonBracketClose = findViewById(R.id.button_close_bracket);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonEquals = findViewById(R.id.button_equals);
        buttonAC = findViewById(R.id.button_ac);
        buttonDot = findViewById(R.id.button_dot);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonBracketOpen.setOnClickListener(this);
        buttonBracketClose.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            dataToCalculate = "";
            return;
        }
        if (buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                if (dataToCalculate.isEmpty())
                    resultTV.setText("0");
            }
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTV.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")) {
            resultTV.setText(finalResult);
        }
    }


    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object evalResult = context.evaluateString(scriptable, data, "Javascript", 1, null);
            if (evalResult == null || evalResult == Context.getUndefinedValue()) {
                return "0";
            }
            String finalResult = evalResult.toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;s
        } catch (Exception e) {
            return "Err";
        }
    }
}