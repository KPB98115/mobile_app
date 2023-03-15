package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView display_view = (TextView) findViewById(R.id.display_view);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_plus) {
            //do math
        }
        else if (v.getId() == R.id.btn_minus) {
            //do math
        }
        else if (v.getId() == R.id.btn_equal) {
            //do math
        }
        else {
            //numeric button
            Button btn = (Button) v;
            String btn_value = btn.getText().toString();

            display_view.setText(display_view.getText() + btn_value);
        }
    }
}

class Calculator {
    private ArrayList<Integer> numeric_stack = new ArrayList();
    private String current_operator = "";
    private int result = 0;

    public Calculator() {

    }

    public void add_number(int number) {
        numeric_stack.add(number);
    }

    public void set_operator(String operator) {
        current_operator = operator;
    }

    public boolean do_math() {
        if (current_operator.length() == 1 && numeric_stack.size() >= 2) {
            if (current_operator.equals("+")) {
                result = numeric_stack.get(0) + numeric_stack.get(1);
            }
            else {
                result = numeric_stack.get(0) - numeric_stack.get(1);
            }
            current_operator = ""; //initial operator
            numeric_stack.removeAll(numeric_stack);
            return true;
        }
        return false;
    }

    public int result() {
        return result;
    }
}