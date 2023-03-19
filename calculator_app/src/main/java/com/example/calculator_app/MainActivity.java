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
        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_9 = findViewById(R.id.btn_9);
        Button btn_plus = findViewById(R.id.btn_plus);
        Button btn_minus = findViewById(R.id.btn_minus);
        Button btn_equal = findViewById(R.id.btn_equal);
        Button btn_clear = findViewById(R.id.btn_clear);

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
        btn_clear.setOnClickListener(this);
    }

    public void btn_input(View v) {
        TextView display_view = findViewById(R.id.display_view);

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
            TextView display_view = findViewById(R.id.display_view);
            String formula = display_view.getText().toString();
            //do math
            if (!calculator.calculate(formula)) {
                System.out.println("something went wrong");
                return;
            }
            display_view.setText(calculator.result());
            calculator.initialize();
        }
        else if (v.getId() == R.id.btn_clear) {
            //empty the textview
            TextView display_view = findViewById(R.id.display_view);
            display_view.setText("");
        }
        else {
            //display the numeric value to textview
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

            //Calculate the formula
            System.out.println("calculate the formula.");
            for (int i=0; i<operations.size(); i++) {
                if (current_operator == "+") {
                    result += operations.get(i);
                }
                else {
                    System.out.println("result: "+result+","+operations);
                    if (result == 0) {
                        result = operations.get(0);
                        continue;
                    }
                    result -= operations.get(i);
                }
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