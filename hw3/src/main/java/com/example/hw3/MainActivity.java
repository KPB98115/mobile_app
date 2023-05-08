package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        TextWatcher,
        RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener {

    Spinner ddList_city;
    Spinner ddList_building;
    Spinner ddList_show;
    EditText ppl_amount;
    RadioGroup ticker_group;
    EditText ticket_number_input;
    EditText datePicker;
    EditText timePicker;
    CheckBox burger;
    CheckBox fries;
    CheckBox coke;
    ArrayList<String> meal_order_array = new ArrayList<>();
    ImageView burger_img;
    ImageView fries_img;
    ImageView coke_img;
    TextView orders;
    TextView results;
    Button submit_btn;

    int show1_price = 800;
    int show2_price = 1000;
    int burger_price = 80;
    int fries_price = 40;
    int coke_price = 50;
    int total_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ddList_city = findViewById(R.id.ddList_city);
        ddList_building = findViewById(R.id.ddList_building);
        ddList_show = findViewById(R.id.ddList_show);
        ppl_amount = findViewById(R.id.ppl_amount);
        ticker_group = findViewById(R.id.ticker_group);
        ticket_number_input = findViewById(R.id.ticket_number_input);
        datePicker = findViewById(R.id.dateEdt);
        timePicker = findViewById(R.id.timeEdt);
        burger = findViewById(R.id.checkBox_burger);
        burger_img = findViewById(R.id.burger_img);
        fries = findViewById(R.id.checkBox_fries);
        fries_img = findViewById(R.id.fries_img);
        coke = findViewById(R.id.checkBox_coke);
        coke_img = findViewById(R.id.coke_img);
        orders = findViewById(R.id.orders);
        results = findViewById(R.id.results);
        submit_btn = findViewById(R.id.submit_btn);

        ppl_amount.addTextChangedListener(this);
        ticker_group.setOnCheckedChangeListener(this);
        ticket_number_input.addTextChangedListener(this);
        datePicker.setOnClickListener(this);
        timePicker.setOnClickListener(this);
        burger.setOnCheckedChangeListener(this);
        fries.setOnCheckedChangeListener(this);
        coke.setOnCheckedChangeListener(this);
        submit_btn.setOnClickListener(this);

        ddList_city.setOnItemSelectedListener(this);
        ddList_building.setOnItemSelectedListener(this);
        ddList_show.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> city_adapter = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> show2_adapter = ArrayAdapter.createFromResource(this,
                R.array.performance2, android.R.layout.simple_spinner_item);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddList_city.setAdapter(city_adapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.dateEdt:
                popup_datePicker(view);
                break;
            case R.id.timeEdt:
                popup_timePicker(view);
                break;
            case R.id.submit_btn:
                if (ticket_number_input.getText().toString() == "0") {
                    Toast.makeText(this, "人數不可為 0", Toast.LENGTH_SHORT).show();
                }
                else {
                    popup_comfrimDialog();
                }
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.ddList_city:
                if (position == 0) {
                    ArrayAdapter<CharSequence> TPE_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TPE, android.R.layout.simple_spinner_item);
                    TPE_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(TPE_building_adapter);
                } else if (position == 1) {
                    ArrayAdapter<CharSequence> TAO_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TAO, android.R.layout.simple_spinner_item);
                    TAO_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(TAO_building_adapter);
                } else if (position == 2) {
                    ArrayAdapter<CharSequence> KAO_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.KAO, android.R.layout.simple_spinner_item);
                    KAO_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(KAO_building_adapter);
                }
                break;
            case R.id.ddList_building:
                if (ddList_building.getSelectedItemPosition() == 0) {
                    ArrayAdapter<CharSequence> show1_adapter = ArrayAdapter.createFromResource(this,
                            R.array.performance1, android.R.layout.simple_spinner_item);
                    show1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_show.setAdapter(show1_adapter);
                } else if (ddList_building.getSelectedItemPosition() == 1) {
                    ArrayAdapter<CharSequence> show2_adapter = ArrayAdapter.createFromResource(this,
                            R.array.performance2, android.R.layout.simple_spinner_item);
                    show2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_show.setAdapter(show2_adapter);
                }
                break;
            case R.id.ddList_show:
                if (ddList_show.getSelectedItemPosition() == 0) {
                    total_price = show1_price;
                } else {
                    total_price = show2_price;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (meal_order_array.contains(compoundButton.getText().toString())) {
            meal_order_array.remove(compoundButton.getText().toString());
        }
        else {
            meal_order_array.add(compoundButton.getText().toString());
        }

        if (isChecked) {
            if (compoundButton.getId() == R.id.checkBox_burger) {
                burger_img.setVisibility(View.VISIBLE);
                if (ticker_group.getCheckedRadioButtonId() == R.id.yes) {
                    int new_meal_price = (int) (burger_price * 0.8);
                    total_price += new_meal_price;
                }
                total_price += burger_price;
            }
            else if (compoundButton.getId() == R.id.checkBox_fries) {
                fries_img.setVisibility(View.VISIBLE);
                if (ticker_group.getCheckedRadioButtonId() == R.id.yes) {
                    int new_meal_price = (int) (fries_price * 0.8);
                    total_price += new_meal_price;
                }
                total_price += fries_price;
            }
            else {
                coke_img.setVisibility(View.VISIBLE);
                if (ticker_group.getCheckedRadioButtonId() == R.id.yes) {
                    int new_meal_price = (int) (coke_price * 0.8);
                    total_price += new_meal_price;
                }
                total_price += coke_price;
            }
        }
        else {
            if (compoundButton.getId() == R.id.checkBox_burger) {
                burger_img.setVisibility(View.INVISIBLE);
                total_price -= burger_price;
            }
            else if (compoundButton.getId() == R.id.checkBox_fries) {
                fries_img.setVisibility(View.INVISIBLE);
                total_price -= fries_price;
            }
            else {
                coke_img.setVisibility(View.INVISIBLE);
                total_price -= coke_price;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
         switch (checkedId) {
             case R.id.yes:
                 ticket_number_input.setEnabled(true);
                 break;
             case R.id.no:
                 ticket_number_input.setEnabled(false);
                 break;
         }
    }

    public void popup_datePicker(View view) {
        Calendar dCalendar = Calendar.getInstance();
        int year = dCalendar.get(Calendar.YEAR);
        int mouth = dCalendar.get(Calendar.MONTH);
        int day = dCalendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(view.getContext(), (view1, year1, month, dayOfMonth) -> {
            String date = year1 + "/" + (month + 1) + "/" + dayOfMonth;
            datePicker.setText(date);
        }, year, mouth, day).show();
    }

    public void popup_timePicker(View view) {
        Calendar tCalendar = Calendar.getInstance();
        int hourOfDay = tCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = tCalendar.get(Calendar.MINUTE);

        new TimePickerDialog(view.getContext(), (view1, hourOfDay1, minute1) -> {
            String time = hourOfDay1 + ":" + minute1;
            timePicker.setText(time);
        }, hourOfDay, minute, true).show();
    }

    public void popup_comfrimDialog() {
        String address = ddList_city.getSelectedItem().toString() + ", " + ddList_building.getSelectedItem().toString();
        String showName = ddList_show.getSelectedItem().toString();
        String amount = ppl_amount.getText().toString();
        String ticket_number = "無";
        if (ticker_group.getCheckedRadioButtonId() == R.id.yes) {
            ticket_number = ticket_number_input.getText().toString();
            total_price = (int) (total_price * 0.8);
        }
        String selectedDate = datePicker.getText().toString();
        String selectedTime = timePicker.getText().toString();
        String meal = "";
        for (String food: meal_order_array) {
            meal += food+", ";
        }

        String result = "地點：" + address
                + "\n劇場：" + showName
                + "\n人數：" + amount
                + "\n優惠票卷號碼：" + ticket_number
                + "\n日期及時間：" + selectedDate + ", " + selectedTime
                + "\n餐點：" + meal
                + "\n總金額：" + total_price;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("確定訂購以下項目嗎？");
        builder.setMessage(
                "劇場：" + showName
                + "人數：" + amount
                + "日期及時間：" + selectedDate + ", " + selectedTime
                + "餐點：" + meal
                );
        builder.setNegativeButton("確認", (dialog, which) -> {
            results.setText(result);
            Toast.makeText(getApplicationContext(), "已成功訂購!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        builder.setPositiveButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }
}

