package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind all button from xml
        Button btn_0 = (Button) findViewById(R.id.btn_0);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        Button btn_7 = (Button) findViewById(R.id.btn_7);
        Button btn_8 = (Button) findViewById(R.id.btn_8);
        Button btn_9 = (Button) findViewById(R.id.btn_9);
        Button btn_plus = (Button) findViewById(R.id.btn_plus);
        Button btn_minus = (Button) findViewById(R.id.btn_minus);
        Button btn_equal = (Button) findViewById(R.id.btn_equal);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    public void btn_input(View v) {
        TextView display_view = (TextView) findViewById(R.id.display_view);

        Button btn = (Button) v;
        String btn_value = btn.getText().toString();

        display_view.setText(display_view.getText() + btn_value);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_plus) {
            //add plus symbol to textview
            btn_input(v);
            //do math
        }
        else if (v.getId() == R.id.btn_minus) {
            //add plus symbol to textview
            btn_input(v);
            //do math

        }
        else if (v.getId() == R.id.btn_equal) {
            TextView display_view = (TextView) findViewById(R.id.display_view);
            String formula = display_view.getText().toString();
            //do math
            if (!calculator.calculate(formula)) {
                System.out.println("something went wrong");
                return;
            }
            display_view.setText(calculator.result());
            calculator.initialize();
        }
        else {
            //numeric button
            btn_input(v);
        }
    }
}

class Calculator {
    private String numeric_stack = "";
    private ArrayList<Integer> operations = new ArrayList<>();
    private String current_operator = "";
    private int result = 0;

    public Calculator() {

    }

    public void add_number(int number) {
        numeric_stack += number;
    }

    public void set_operator(String operator) {
        current_operator = operator;
    }

    public boolean do_math() {
        if (current_operator.length() == 1 && numeric_stack.length() >= 2) {
            if (current_operator.equals("+")) {
                result = numeric_stack.charAt(0) + numeric_stack.charAt(1);
            }
            else {
                result = numeric_stack.charAt(0) - numeric_stack.charAt(1);
            }
            current_operator = ""; //initial operator
            numeric_stack = "";
            return true;
        }
        return false;
    }

    public Boolean calculate(String formula) {
        //Check the last character to verify it is invalid formula || if the input only have numeric value
        if (Character.getNumericValue(formula.charAt(formula.length()-1)) == -1) {
            System.out.println("formula is invalid.");
            return false;
        }
        else {
            //Loop through the user input
            for (int i=0; i<formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    set_operator("+");
                    //combine stack value into an operations
                    int num = Integer.parseInt(numeric_stack);
                    operations.add(num);
                    //initialize the numeric stack
                    numeric_stack = "";
                }
                else if (formula.charAt(i) == '-') {
                    set_operator("-");
                    //combine stack value into an operations
                    int num = Integer.parseInt(numeric_stack);
                    operations.add(num);
                    //initialize the numeric stack
                    numeric_stack = "";
                }
                else { //otherwise append numeric value to stack
                    add_number(Character.getNumericValue(formula.charAt(i)));
                }
            }
            //combine the last value from the stack into an operations
            int num = Integer.parseInt(numeric_stack);
            operations.add(num);
            //initialize the numeric stack
            numeric_stack = "";

            //Calculate the formula
            System.out.println("calculate the formula.");
            if (current_operator == "+") {
                result = result + operations.get(0) + operations.get(1);
            }
            else if (current_operator == "-") {
                result = result + operations.get(0) - operations.get(1);
            }
            else {
                System.out.println("exception result");
                result = 0;
            }
            //initialize the class property
            operations.clear();
            numeric_stack = "";
            current_operator = "";
        }

        return true;
    }

    public String result() {
        System.out.println(result);
        return String.valueOf(result);
    }

    public void initialize() {
        result = 0;
    }
}