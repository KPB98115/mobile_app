package com.example.mobile_app;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobile_app.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hw1_submit_btn(View view) {
        EditText input = (EditText) findViewById(R.id.input_field);
        TextView stdInfo = (TextView) findViewById(R.id.stdInfo);
        String text = "B0944145施家浩";

        stdInfo.setText(input.getEditableText().toString()+", "+text);
    }

    public void hw1_bigger_btn(View view) {
        float size = 45 % 40; //45 mod 5 = 0, use 45 mod 40 instead.

        TextView stdInfo = (TextView) findViewById(R.id.stdInfo);
        stdInfo.setTextSize(stdInfo.getTextSize() + size);
    }

    public void hw1_smaller_btn(View view) {
        float size = 45 % 40; //45 mod 5 = 0, use 45 mod 40 instead.

        TextView stdInfo = (TextView) findViewById(R.id.stdInfo);
        stdInfo.setTextSize(size);
    }

    public void hw2_onClick(View view) {
        TextView textView = (TextView) findViewById(R.id.hw2_textview);
        Button btn = (Button) view;
        String btn_value = btn.getText().toString();

        textView.setText(textView.getText() + btn_value);
    }

    public void hw2_operator(View view) {
        Button btn = (Button) view;
        String btn_value = btn.getText().toString();

        TextView textView = (TextView) findViewById(R.id.hw2_textview);
        int current_value = Integer.parseInt(textView.getText().toString());

        calculator.add_number(current_value);
        calculator.set_operator(btn_value);

        textView.setText("");
    }

    public void hw2_calculate(View view) {
        TextView textView = (TextView) findViewById(R.id.hw2_textview);
        int current_value = Integer.parseInt(textView.getText().toString());

        calculator.add_number(current_value);
        if (calculator.do_math()) {
            textView.setText(calculator.result()+"");
        }

        //Toast.makeText(this, current_value, Toast.LENGTH_SHORT).show();
    }

    public void hw3_submit(View view) {
        EditText input = (EditText) findViewById(R.id.hw3_input);
        TextView output = (TextView) findViewById(R.id.hw3_textview);
        int input_num = Integer.parseInt(input.getEditableText().toString());

        output.setText("");

        for (int i=0; i<input_num; i++) {
            for (int j=1; j<=2*i-1;j++) {
                output.setText(output.getText()+"*");
            }
            output.setText(output.getText()+"\n");
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
