package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import org.w3c.dom.Text;

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
    RadioGroup rbtn_group;
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
    LinearLayout order;
    LinearLayout results;
    Button submit_btn;
    TextView date, time, location, show, ticket_amount, ticket_number, meal_order, price;

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
        ppl_amount.addTextChangedListener(this);
        rbtn_group.setOnCheckedChangeListener(this);
        ticket_number_input = findViewById(R.id.ticket_number_input);
        ticket_number_input.addTextChangedListener(this);
        datePicker = findViewById(R.id.dateEdt);
        timePicker = findViewById(R.id.timeEdt);
        burger.setOnCheckedChangeListener(this);
        fries.setOnCheckedChangeListener(this);
        coke.setOnCheckedChangeListener(this);
        burger_img = findViewById(R.id.burger_img);
        fries_img = findViewById(R.id.fries_img);
        coke_img = findViewById(R.id.coke_img);
        order = findViewById(R.id.order);
        results = findViewById(R.id.results);
        submit_btn = findViewById(R.id.submit_btn);

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        ticket_number = findViewById(R.id.ticket_number);
        ticket_amount = findViewById(R.id.ticket_amount);
        location = findViewById(R.id.location);
        show = findViewById(R.id.show);
        meal_order = findViewById(R.id.meal_order);
        price = findViewById(R.id.price);

        ddList_city.setOnItemSelectedListener(this);
        ddList_building.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> city_adapter = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);


        ArrayAdapter<CharSequence> show2_adapter = ArrayAdapter.createFromResource(this,
                R.array.performance2, android.R.layout.simple_spinner_item);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddList_city.setAdapter(city_adapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.ddList_city:
                String city = ddList_city.getSelectedItem().toString();
                if (city == "TPE") {
                    ArrayAdapter<CharSequence> TPE_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TPE, android.R.layout.simple_spinner_item);
                    TPE_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(TPE_building_adapter);
                }
                else if (city == "TAO") {
                    ArrayAdapter<CharSequence> TAO_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TAO, android.R.layout.simple_spinner_item);
                    TAO_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(TAO_building_adapter);
                }
                else if (city == "KAO") {
                    ArrayAdapter<CharSequence> KAO_building_adapter = ArrayAdapter.createFromResource(this,
                            R.array.KAO, android.R.layout.simple_spinner_item);
                    KAO_building_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_building.setAdapter(KAO_building_adapter);
            }
            case R.id.ddList_building:
                if (!ddList_building.isSelected()) {
                    return;
                }
                if (ddList_building.getSelectedItemPosition() == 0) {
                    ArrayAdapter<CharSequence> show1_adapter = ArrayAdapter.createFromResource(this,
                            R.array.performance1, android.R.layout.simple_spinner_item);
                    show1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_show.setAdapter(show1_adapter);
                }
                else if (ddList_building.getSelectedItemPosition() == 1) {
                    ArrayAdapter<CharSequence> show2_adapter = ArrayAdapter.createFromResource(this,
                            R.array.performance2, android.R.layout.simple_spinner_item);
                    show2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_show.setAdapter(show2_adapter);
                }
            case R.id.ddList_show:
                String full_location = ddList_city.getSelectedItem().toString() + ddList_city.getSelectedItem().toString();
                location.setText(full_location);
                show.setText(ddList_show.getSelectedItem().toString());
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
        ticket_amount.setText(ticket_amount.getText());
        ticket_number.setText(ticket_number_input.getText());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            switch (compoundButton.getId()) {
                case R.id.yes:
                    ticket_number_input.setEnabled(false);
                case R.id.no:
                    ticket_number_input.setEnabled(true);
                case R.id.cb_burger:
                    burger_img.setVisibility(View.VISIBLE);
                    total_price += burger_price;
                case R.id.cb_fries:
                    fries_img.setVisibility(View.VISIBLE);
                    total_price = fries_price;
                case R.id.cb_coke:
                    coke_img.setVisibility(View.VISIBLE);
                    total_price = coke_price;
            }
        }
        else {
            switch (compoundButton.getId()) {
                case R.id.cb_burger:
                    burger_img.setVisibility(View.GONE);
                    meal_order_array.remove(burger.toString());
                case R.id.cb_fries:
                    fries_img.setVisibility(View.GONE);
                    meal_order_array.remove(fries.toString());
                case R.id.cb_coke:
                    coke_img.setVisibility(View.GONE);
                    meal_order_array.remove(coke.toString());
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

    }

    public void datePicker(EditText dateEdt) {
        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                MainActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // on below line we are setting date to our edit text.
                    dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);

                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }

    public void timePicker(EditText timeEdt) {
        final Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                (timePicker, selectedHour, selectedMinute) -> {
                    timeEdt.setText(hour + ":" + minute + ((hour > 12) ? "am" : "pm"));
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public void write_order(String date, String time, String location, String show, int amount,
                            String ticket_numebr, String meal_order, int price) {
        TextView order = new TextView(this);
        order.setText(
                "日期：" + date +
                "時間：" + time +
                "地點：" + location +
                "表演場次：" + show +
                "購票數量：" + amount + "張" +
                "票卷編號：" + ticket_numebr +
                "餐點：" + meal_order +
                "總額：" + price + "元"
        );

        results.addView(order);
    }
}

