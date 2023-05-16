package com.example.final_exam;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Ticket_order {
    public double child_discount = 0.5;
    public double older_discount = 0.6;
    public double disable_discount = 0.5;
    public double collage_discount = 0.8;
    public List<String> station = List.of("南港", "臺北", "板橋", "桃園", "新竹", "苗栗", "臺中", "彰化", "雲林", "嘉義", "臺南", "左營");
    public int[][] highSpeedRail_price = {
            {0},
            {0},
            {40, 0},
            {70, 40, 0},
            {200, 160, 130, 0},
            {330, 290, 260, 130, 0},
            {480, 430, 400, 280, 140, 0},
            {750, 700, 670, 540, 410, 270, 0},
            {870, 820, 790, 670, 540, 390, 130, 0},
            {970, 930, 900, 780, 640, 500, 230, 110, 0},
            {1120, 1080, 1050, 920, 790, 640, 380, 250, 150, 0},
            {1390, 1350, 1320, 1190, 1060, 920, 650, 530, 420, 280, 0},
            {1530, 1490, 1460, 1330, 1200, 1060, 790, 670, 560, 410, 140, 0}
    };

    private int origin_index;
    private int destination_index;
    private String date_of_train;
    private String time_of_train;
    private ArrayList<Integer[]> passage_info; // Each element in arraylist is [{category}, {amount}]
    private int adult_amount;
    private int child_amount;
    private int older_amount;
    private int disabled_amount;
    private int collage_amount;
    private int total_price;

    public Ticket_order(int origin_index, int destination_index, String date, String time, ArrayList<Integer[]> passage) {
        this.origin_index = origin_index;
        this.destination_index = destination_index;
        this.date_of_train = date;
        this.time_of_train = time;
        this.passage_info = passage;
        for (Integer[] person: passage) {
            if (person[0] == 0) {
                this.adult_amount = person[1];
            }
            else if (person[0] == 1) {
                this.child_amount = person[1];
            }
            else if (person[0] == 2) {
                this.older_amount = person[1];
            }
            else if (person[0] == 3) {
                this.disabled_amount = person[1];
            }
            else if (person[0] == 4) {
                this.collage_amount = person[1];
            }
        }

        calculateTotalPrice(); // Calculate the total price of passage once instance created.

    }

    public boolean calculateTotalPrice() {
        int per_price;

        if (this.origin_index > this.destination_index) {
            per_price = this.highSpeedRail_price[this.origin_index][this.destination_index];
        } else {
            per_price = this.highSpeedRail_price[this.destination_index][this.origin_index];
        }

        this.total_price += per_price * this.adult_amount;
        this.total_price += (int) (per_price * this.child_amount * child_discount);
        this.total_price += (int) (per_price * this.older_amount * older_discount);
        this.total_price += (int) (per_price * this.disabled_amount * disable_discount);
        this.total_price += (int) (per_price * this.collage_amount * collage_discount);

        if (this.total_price == 0) return false;
        return true;
    }

    public int getOrigin_index() {
        return this.origin_index;
    }

    public int getDestination_index() {
        return this.destination_index;
    }

    public String getDate_of_train() {
        return this.date_of_train;
    }

    public String getTime_of_train() {
        return this.time_of_train;
    }

    public int getAdult_amount() {
        return this.adult_amount;
    }

    public int getChild_amount() {
        return this.child_amount;
    }

    public int getOlder_amount() {
        return this.older_amount;
    }

    public int getDisabled_amount() {
        return this.disabled_amount;
    }

    public int getCollage_amount() {
        return this.collage_amount;
    }

    public int getTotalPrice() {
        return this.total_price;
    }

    /**
     * This method only receive origin, destination as parameter.
     * Return false if any properties are not edited successfully.
     */
    public boolean modifyStationProperties(String station, int index) {
        if (station.equals("origin")) {
            this.origin_index = index;
        }
        else if (station.equals("destination")) {
            this.destination_index = index;
        }
        else {
            return false;
        }

        calculateTotalPrice(); // Calculate and update the total price once properties has modified.

        return true;
    }

    /**
     * This method only receive date and time as parameter.
     * Return false if any properties are not edited successfully.
     */
    public boolean modifyDateTimeProperties(String prop, String value) {
        if (prop.equals("date")) {
            this.date_of_train = value;
        }
        else if (prop.equals("time")) {
            this.time_of_train = value;
        }
        else {
            return false;
        }

        calculateTotalPrice(); // Calculate and update the total price once properties has modified.

        return true;
    }

    /**
     * @param passage This parameter should contain int array( e.g. [{category}, {amount}] ).
     * Which first element represent category, second element represent amount of people.
     * 0 for adult, 1 for child, 2 for older, 3 for disabled, 4 for collage student.
     * Return false if no passage info has edited.
     */
    public boolean modifyPassageProperties(ArrayList<Integer[]> passage) {
        for (Integer[] person: passage) {
            if (person[0] == 0) {
                this.adult_amount = person[1];
            }
            else if (person[0] == 1) {
                this.child_amount = person[1];
            }
            else if (person[0] == 2) {
                this.older_amount = person[1];
            }
            else if (person[0] == 3) {
                this.disabled_amount = person[1];
            }
            else if (person[0] == 4) {
                this.collage_amount = person[1];
            }
            else {
                return false;
            }
        }

        calculateTotalPrice(); // Calculate and update the total price once properties has modified.

        return true;
    }
}
