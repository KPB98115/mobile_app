package com.example.check_box_form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    RadioGroup rbtn_group;
    EditText user_input;
    Button submit_btn;
    TextView result;

    CheckBox burger;
    CheckBox fries;
    CheckBox coke;
    CheckBox icecream;
    Button menu_submit_btn;
    TextView receipt;
    ArrayList<String> order = new ArrayList<>();

    ImageView burger_img;
    ImageView fries_img;
    ImageView coke_img;
    ImageView icecream_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_input = findViewById(R.id.user_input);
        submit_btn = findViewById(R.id.submit_btn);
        rbtn_group = findViewById(R.id.rbtn_group);
        result = findViewById(R.id.result);

        user_input.addTextChangedListener(this);
        rbtn_group.setOnCheckedChangeListener(this);

        burger = findViewById(R.id.cb_burger);
        fries = findViewById(R.id.cb_fries);
        coke = findViewById(R.id.cb_coke);
        icecream = findViewById(R.id.cb_icecream);
        receipt = findViewById(R.id.receipt);
        menu_submit_btn = findViewById(R.id.menu_submit_btn);

        burger_img = findViewById(R.id.burger_img);
        fries_img = findViewById(R.id.fries_img);
        coke_img = findViewById(R.id.coke_img);
        icecream_img = findViewById(R.id.icecream_img);

        burger.setOnCheckedChangeListener(this);
        fries.setOnCheckedChangeListener(this);
        coke.setOnCheckedChangeListener(this);
        icecream.setOnCheckedChangeListener(this);
        menu_submit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String text = user_input.getText().toString();

        if (view.getId() == R.id.rbtn_group) {
            if (text != "") {
                int amount = Integer.parseInt(text);
                result.setText("Total: $"+calculate(amount, rbtn_group.getCheckedRadioButtonId()));
            }
            else {
                result.setText("Please enter amount.");
            }
        }
        else if (view.getId() == R.id.menu_submit_btn) {
            burger_img.setVisibility(View.GONE);
            fries_img.setVisibility(View.GONE);
            coke_img.setVisibility(View.GONE);
            icecream_img.setVisibility(View.GONE);
            receipt.setText("Receipt : ");

            String newOrder = "";
            //loop through the arraylist to list all items in the textview
            for (String meal: order) {
                newOrder += meal+" ";

                //check if the selected item, to show the item img
                switch (meal) {
                    case "Hamburger":
                        burger_img.setVisibility(View.VISIBLE);
                        break;
                    case "Fries":
                        fries_img.setVisibility(View.VISIBLE);
                        break;
                    case "Coke":
                        coke_img.setVisibility(View.VISIBLE);
                        break;
                    case "Ice cream":
                        icecream_img.setVisibility(View.VISIBLE);
                        break;
                }
            }

            receipt.setText(receipt.getText().toString()+"\t\t"+newOrder);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int amount = Integer.parseInt(user_input.getText().toString());
        result.setText("Total: $"+calculate(amount, checkedId));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //If the option is check, add the item into arraylist
        if (isChecked) {
            order.add(buttonView.getText().toString());
        }
        //If the option is not checked, remove the item from the arraylist
        else {
            order.remove(buttonView.getText().toString());
        }
    }

    public int calculate(int amount, int rbtn_id) {
        int price = 100;

        switch (rbtn_id) {
            case R.id.elder:
                price = (int)(price*0.8*amount);
                return price;
            case R.id.child:
                price = (int)(price*0.5*amount);
                return price;
        }
        return price;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        int amount;

        if (!user_input.getText().toString().equals("")) {
            if (rbtn_group.getCheckedRadioButtonId() != -1) {
                amount = Integer.parseInt(user_input.getText().toString());
                result.setText("Total: $"+calculate(amount, rbtn_group.getCheckedRadioButtonId()));
            }
            else {
                result.setText("Please enter amount.");
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        int amount;

        if (!user_input.getText().toString().equals("")) {
            if (rbtn_group.getCheckedRadioButtonId() != -1) {
                amount = Integer.parseInt(user_input.getText().toString());
                result.setText("Total: $"+calculate(amount, rbtn_group.getCheckedRadioButtonId()));
            }
            else {
                result.setText("Please enter amount.");
            }
        }
    }
}