package com.example.cinema_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner ddList_city;
    Spinner ddList_type;
    Spinner ddList_cinema;
    EditText client_name;
    Button submit_btn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ddList_city = findViewById(R.id.ddList_city);
        ddList_type = findViewById(R.id.ddList_type);
        ddList_cinema = findViewById(R.id.ddList_cinema);
        client_name = findViewById(R.id.client_name);
        submit_btn = findViewById(R.id.submit_btn);
        result = findViewById(R.id.result);

        ddList_city.setOnItemSelectedListener(this);
        ddList_type.setOnItemSelectedListener(this);
        submit_btn.setOnClickListener(this);

        ArrayAdapter<CharSequence> city_adapter = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> cinema_TAO_adapter = ArrayAdapter.createFromResource(this,
                R.array.TAO, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> cinema_TAI_adapter = ArrayAdapter.createFromResource(this,
                R.array.TAI, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> cinema_KAO_adapter = ArrayAdapter.createFromResource(this,
                R.array.KAO, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ddList_city.setAdapter(city_adapter);
        ddList_type.setAdapter(type_adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (!ddList_city.isSelected() && !ddList_cinema.isSelected() && !ddList_type.isSelected()) {
                    result.setText("Please select item form dropdown list.");
                }
                else {
                    String type = ddList_type.getSelectedItem().toString();
                    String city = ddList_city.getSelectedItem().toString();
                    String cinema = ddList_cinema.getSelectedItem().toString();

                    result.setText("1 "+type+", "+cinema+", "+city);
                }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(view.getId()) {
            case R.id.ddList_city:
                String city = ddList_city.getSelectedItem().toString();
                if (city == "TPE") {
                    ArrayAdapter<CharSequence> cinema_TPE_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TPE, android.R.layout.simple_spinner_item);
                    cinema_TPE_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_cinema.setAdapter(cinema_TPE_adapter);
                }
                else if (city == "TAO") {
                    ArrayAdapter<CharSequence> cinema_TAO_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TAO, android.R.layout.simple_spinner_item);
                    cinema_TAO_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_cinema.setAdapter(cinema_TAO_adapter);
                }
                else if (city == "TAI") {
                    ArrayAdapter<CharSequence> cinema_TAI_adapter = ArrayAdapter.createFromResource(this,
                            R.array.TAI, android.R.layout.simple_spinner_item);
                    cinema_TAI_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_cinema.setAdapter(cinema_TAI_adapter);
                }
                else if (city == "KAO") {
                    ArrayAdapter<CharSequence> cinema_KAO_adapter = ArrayAdapter.createFromResource(this,
                            R.array.KAO, android.R.layout.simple_spinner_item);
                    cinema_KAO_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ddList_cinema.setAdapter(cinema_KAO_adapter);
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}