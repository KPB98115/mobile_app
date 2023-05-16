package com.example.final_exam;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Booking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Booking extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, TextWatcher {

    Spinner sp_origin;
    Spinner sp_destination;
    EditText datePicker;
    EditText timePicker;
    Spinner adult_amount;
    Spinner child_amount;
    Spinner disabled_amount;
    Spinner older_amount;
    Spinner collage_amount;
    LinearLayout parent_layout;
    Button ticket_order;
    Button ticket_cancel;
    Button ticket_edit;

    ArrayList<Integer[]> passage = new ArrayList<>();
    ArrayList<Ticket_order> orders = new ArrayList<>();

    public Booking() {
        // Required empty public constructor
    }

    public static Booking newInstance(String param1, String param2) {
        Booking fragment = new Booking();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        sp_origin = view.findViewById(R.id.sp_origin);
        sp_destination = view.findViewById(R.id.sp_destination);
        datePicker = view.findViewById(R.id.edt_date);
        timePicker = view.findViewById(R.id.edt_time);
        adult_amount = view.findViewById(R.id.sp_adult);
        child_amount = view.findViewById(R.id.sp_child);
        disabled_amount = view.findViewById(R.id.sp_disabled);
        older_amount = view.findViewById(R.id.sp_older);
        collage_amount = view.findViewById(R.id.sp_collage_std);
        parent_layout = view.findViewById(R.id.parent_layout);
        ticket_order = view.findViewById(R.id.ticket_order);
        ticket_cancel = view.findViewById(R.id.ticket_cancel);
        ticket_edit = view.findViewById(R.id.ticket_edit);

        sp_origin.setOnItemSelectedListener(this);
        sp_destination.setOnItemSelectedListener(this);
        datePicker.setOnClickListener(this);
        timePicker.setOnClickListener(this);
        adult_amount.setOnItemSelectedListener(this);
        child_amount.setOnItemSelectedListener(this);
        older_amount.setOnItemSelectedListener(this);
        disabled_amount.setOnItemSelectedListener(this);
        collage_amount.setOnItemSelectedListener(this);
        ticket_order.setOnClickListener(this);

        ArrayAdapter<CharSequence> origin_station = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.station, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> destination_station = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.station, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> numbers = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.number, android.R.layout.simple_spinner_item);
        sp_origin.setAdapter(origin_station);
        sp_destination.setAdapter(destination_station);
        adult_amount.setAdapter(numbers);
        child_amount.setAdapter(numbers);
        older_amount.setAdapter(numbers);
        disabled_amount.setAdapter(numbers);
        collage_amount.setAdapter(numbers);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_date:
                popup_datePicker(v);
                break;
            case R.id.edt_time:
                popup_timePicker(v);
                break;
            case R.id.ticket_order:
                popup_confirmDialog();
                break;
            case R.id.ticket_cancel:
                break;
            case R.id.ticket_edit:

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_adult:
                Integer[] adult = {0, position};
                passage.add(adult);
                break;
            case R.id.sp_child:
                Integer[] child = {1, position};
                passage.add(child);
                break;
            case R.id.sp_older:
                Integer[] older = {2, position};
                passage.add(older);
                break;
            case R.id.sp_disabled:
                Integer[] disabled = {3, position};
                passage.add(disabled);
                break;
            case R.id.sp_collage_std:
                Integer[] collage = {4, position};
                passage.add(collage);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void appendOrderTable(Ticket_order order) {
        TextView origin = new TextView(getContext());
        TextView destination = new TextView(getContext());
        TextView date = new TextView(getContext());
        TextView time = new TextView(getContext());
        TextView passage = new TextView(getContext());
        TextView totalPrice = new TextView(getContext());
        TableLayout tableLayout = new TableLayout(getContext());
        TableRow location_row = new TableRow(getContext());
        TableRow datetime_row = new TableRow(getContext());
        TableRow passage_row = new TableRow(getContext());

        origin.setText("起始站：" + sp_origin.getItemAtPosition(order.getOrigin_index()).toString());
        origin.setGravity(Gravity.LEFT);
        origin.setBackgroundResource(R.drawable.border);
        origin.setPadding(10, 10, 10, 10);

        destination.setText("終點站：" + sp_destination.getItemAtPosition(order.getDestination_index()));
        destination.setGravity(Gravity.RIGHT);
        destination.setBackgroundResource(R.drawable.border);
        destination.setPadding(10, 10, 10, 10);

        date.setText("發車日期：" + order.getDate_of_train());
        date.setGravity(Gravity.LEFT);
        date.setBackgroundResource(R.drawable.border);
        date.setPadding(10, 10, 10, 10);

        time.setText("發車時間：" + order.getTime_of_train());
        time.setGravity(Gravity.RIGHT);
        time.setBackgroundResource(R.drawable.border);
        time.setPadding(10, 10, 10, 10);

        passage.setText("乘客數量：");
        passage.setGravity(Gravity.LEFT);
        passage.setBackgroundResource(R.drawable.border);
        passage.setPadding(10, 10, 10, 10);

        totalPrice.setText("金額：" + order.getTotalPrice());
        totalPrice.setGravity(Gravity.RIGHT);
        totalPrice.setBackgroundResource(R.drawable.border);
        totalPrice.setPadding(10, 10, 10, 10);

        if (order.getAdult_amount() > 0) {
            passage.setText(passage.getText() + "全票" + order.getAdult_amount() + "張 ");
        }
        if (order.getChild_amount() > 0) {
            passage.setText(passage.getText() + "孩童票" +  order.getChild_amount() + "張 ");
        }
        if (order.getOlder_amount() > 0) {
            passage.setText(passage.getText() + "敬老票" + order.getOlder_amount() + "張 ");
        }
        if (order.getDisabled_amount() > 0) {
            passage.setText(passage.getText() + "愛心票" + order.getDisabled_amount() + "張 ");
        }
        if (order.getCollage_amount() > 0) {
            passage.setText(passage.getText() + "大學生票" + order.getCollage_amount() + "張 ");
        }

        location_row.addView(origin);
        location_row.addView(destination);
        location_row.setGravity(Gravity.CENTER);
        datetime_row.addView(date);
        datetime_row.addView(time);
        datetime_row.setGravity(Gravity.CENTER);
        passage_row.addView(passage);
        passage_row.addView(totalPrice);
        passage_row.setGravity(Gravity.CENTER);

        tableLayout.addView(location_row);
        tableLayout.addView(datetime_row);
        tableLayout.addView(passage_row);
        tableLayout.setOnClickListener(view -> {
            sp_origin.setSelection(order.getOrigin_index());
            sp_destination.setSelection(order.getDestination_index());
            datePicker.setText(order.getDate_of_train());
            timePicker.setText(order.getTime_of_train());
            adult_amount.setSelection(order.getAdult_amount());
            child_amount.setSelection(order.getChild_amount());
            older_amount.setSelection(order.getOlder_amount());
            disabled_amount.setSelection(order.getDisabled_amount());
            collage_amount.setSelection(order.getCollage_amount());
            ticket_edit.setEnabled(true);
            ticket_cancel.setEnabled(true);
            ticket_order.setEnabled(false);

            ticket_edit.setOnClickListener(btnView -> {
                // Not enough time to finish this part...
            });

            ticket_cancel.setOnClickListener(btnView -> {
                popup_CancelDialog(view);
                ticket_order.setEnabled(true);
                ticket_edit.setEnabled(false);
                ticket_cancel.setEnabled(false);
            });
        });

        parent_layout.addView(tableLayout);

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
        NumberPicker numberPicker = new NumberPicker(view.getContext());
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(23);
        String[] pickTime = new String[] {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
                "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00",
                "09:30", "10:00", "10:30", "11:00", "11:30"};
        numberPicker.setDisplayedValues(pickTime);
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            timePicker.setText(pickTime[picker.getValue()]);
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(numberPicker);
        builder.setTitle("請選擇時間");
        builder.setNegativeButton("確認", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    public void popup_confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle("確定訂購所填寫的項目嗎？");
        builder.setNegativeButton("確認", (dialog, which) -> {
            Ticket_order order = new Ticket_order( // Create an order instance
                    sp_origin.getSelectedItemPosition(),
                    sp_destination.getSelectedItemPosition(),
                    datePicker.getText().toString(),
                    timePicker.getText().toString(),
                    passage
            );
            orders.add(order); // And add into orders array
            Toast.makeText(getActivity(), "已成功訂購!", Toast.LENGTH_SHORT).show();
            appendOrderTable(order);
            dialog.dismiss();
        });
        builder.setPositiveButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void popup_CancelDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle("確定要刪除所選取的項目嗎？");
        builder.setNegativeButton("確認", (dialog, which) -> {
            ((ViewGroup) view.getParent()).removeView(view);
            Toast.makeText(getActivity(), "已成功刪除!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        builder.setPositiveButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}