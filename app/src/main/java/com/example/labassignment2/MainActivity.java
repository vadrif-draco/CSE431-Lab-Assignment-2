package com.example.labassignment2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

  TextView input_tv;

  TextView result_tv;

  Button btn_clr;
  Button btn_sqrt;
  Button btn_sqr;
  Button btn_div;

  Button btn_num7;
  Button btn_num8;
  Button btn_num9;
  Button btn_mul;

  Button btn_num4;
  Button btn_num5;
  Button btn_num6;
  Button btn_min;

  Button btn_num1;
  Button btn_num2;
  Button btn_num3;
  Button btn_pls;

  Button btn_back;
  Button btn_num0;
  Button btn_dot;
  Button btn_eq;

  class NumberParser implements View.OnClickListener {

    String number;

    NumberParser(Integer number) {
      this.number = String.valueOf(number);
    }

    @Override
    public void onClick(View view) {
      input_tv.setText(input_tv.getText() + number);
      // If operation is "", this means we're still filling the first operand
      if (operation.equals("")) {
        operand1 += this.number;
      } else {
        operand2 += this.number;
      }
    }
  }

  class OperationParser implements View.OnClickListener {

    String operation;

    OperationParser(String operation) {
      this.operation = operation;
    }

    @Override
    public void onClick(View view) {
      input_tv.setText(input_tv.getText() + " " + this.operation + " ");
      MainActivity.this.operation = this.operation;
    }
  }

  String operation = "";
  String operand1 = "";
  String operand2 = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Input and result text views

    input_tv = findViewById(R.id.input);
    result_tv = findViewById(R.id.result);

    // Row #0

    btn_clr = findViewById(R.id.btn_clr);
    btn_clr.setOnClickListener(view -> {
      operation = operand1 = operand2 = "";
      input_tv.setText("");
      result_tv.setText("");
    });

    btn_sqrt = findViewById(R.id.btn_sqrt);
    btn_sqrt.setOnClickListener(view -> {

      Double result;

      if (operation.equals("")) {
        result = Math.sqrt(Double.parseDouble(operand1));
      } else {
        result = Math.sqrt(Double.parseDouble(operand2));
      }

      input_tv.setText(String.valueOf(result));
      result_tv.setText(String.valueOf(result));
      operand1 = String.valueOf(result);
      operation = operand2 = "";

    });

    btn_sqr = findViewById(R.id.btn_sqr);
    btn_sqr.setOnClickListener(view -> {

      Double result;

      if (operation.equals("")) {
        result = Double.parseDouble(operand1) * Double.parseDouble(operand1);
      } else {
        result = Double.parseDouble(operand2) * Double.parseDouble(operand2);
      }

      input_tv.setText(String.valueOf(result));
      result_tv.setText(String.valueOf(result));
      operand1 = String.valueOf(result);
      operation = operand2 = "";

    });

    btn_div = findViewById(R.id.btn_div);
    btn_div.setOnClickListener(new OperationParser(this.getResources().getString(R.string.div)));

    // Row #1

    btn_num7 = findViewById(R.id.btn_num7);
    btn_num7.setOnClickListener(new NumberParser(7));

    btn_num8 = findViewById(R.id.btn_num8);
    btn_num8.setOnClickListener(new NumberParser(8));

    btn_num9 = findViewById(R.id.btn_num9);
    btn_num9.setOnClickListener(new NumberParser(9));

    btn_mul = findViewById(R.id.btn_mul);
    btn_mul.setOnClickListener(new OperationParser(this.getResources().getString(R.string.mul)));

    // Row #2

    btn_num4 = findViewById(R.id.btn_num4);
    btn_num4.setOnClickListener(new NumberParser(4));

    btn_num5 = findViewById(R.id.btn_num5);
    btn_num5.setOnClickListener(new NumberParser(5));

    btn_num6 = findViewById(R.id.btn_num6);
    btn_num6.setOnClickListener(new NumberParser(6));

    btn_min = findViewById(R.id.btn_min);
    btn_min.setOnClickListener(new OperationParser(this.getResources().getString(R.string.min)));

    // Row #3

    btn_num1 = findViewById(R.id.btn_num1);
    btn_num1.setOnClickListener(new NumberParser(1));

    btn_num2 = findViewById(R.id.btn_num2);
    btn_num2.setOnClickListener(new NumberParser(2));

    btn_num3 = findViewById(R.id.btn_num3);
    btn_num3.setOnClickListener(new NumberParser(3));

    btn_pls = findViewById(R.id.btn_pls);
    btn_pls.setOnClickListener(new OperationParser(this.getResources().getString(R.string.pls)));

    // Row #4

    btn_back = findViewById(R.id.btn_back);
    btn_back.setOnClickListener(view -> {
      String current = input_tv.getText().toString();
      if (current.length() > 0) {
        input_tv.setText(current.substring(0, current.length() - 1));
        if (!operand2.equals("")) {
          operand2 = operand2.substring(0, operand2.length() - 1);
          if (operand2.length() == 0) operand2 = "";
        } else if (!operation.equals("")) {
          operation = "";
        } else {
          operand1 = operand1.substring(0, operand1.length() - 1);
          if (operand1.length() == 0) operand1 = "";
        }
      }
    });

    btn_num0 = findViewById(R.id.btn_num0);
    btn_num0.setOnClickListener(new NumberParser(0));

    btn_dot = findViewById(R.id.btn_dot);
    btn_dot.setOnClickListener(view -> {
      input_tv.setText(input_tv.getText() + ".");
      if (!operand1.equals("")) {
        operand1 += ".";
      } else {
        operand2 += ".";
      }
      // TODO: Try and see what happens if you try to perform an operation with an operand ending with a dot
    });

    btn_eq = findViewById(R.id.btn_eq);
    btn_eq.setOnClickListener(view -> {

      if (!operand1.equals("") && !operation.equals("") && !operand2.equals("")) {

        Double op1 = Double.valueOf(operand1);
        Double op2 = Double.valueOf(operand2);
        double result;

        if (operation.equals(this.getResources().getString(R.string.div))) {
          result = op1 / op2;
        } else if (operation.equals(this.getResources().getString(R.string.mul))) {
          result = op1 * op2;
        } else if (operation.equals(this.getResources().getString(R.string.min))) {
          result = op1 - op2;
        } else if (operation.equals(this.getResources().getString(R.string.pls))) {
          result = op1 + op2;
        } else {
          result = 0.0;
        }

        input_tv.setText(String.valueOf(result));
        result_tv.setText(String.valueOf(result));
        operand1 = String.valueOf(result);
        operation = operand2 = "";
      }
    });

  }
}