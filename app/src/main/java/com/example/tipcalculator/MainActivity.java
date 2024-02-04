package com.example.tipcalculator;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tipcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    TextView total_per_person_view;
    EditText total_bill_edit;
    EditText tip_percentage_edit;
    EditText num_of_people_edit;

    String total_per_person;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        total_per_person_view = binding.totalPerPersonView;
        total_bill_edit = binding.totalBillEdit;
        tip_percentage_edit = binding.tipPercentageEdit;
        num_of_people_edit = binding.numOfPeopleEdit;

        binding.calculateButton.setOnClickListener(v -> {
            total_per_person = String.format("Total Per Person: $%.2f", calculate_tip());
            total_per_person_view.setText(total_per_person);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("total_per_person", total_per_person);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        total_per_person_view.setText(savedInstanceState.getString("total_per_person"));
    }

    public float calculate_tip() {
        float tip = 0.0F;
        float total_bill = Float.parseFloat(total_bill_edit.getText().toString());
        float tip_percentage = Float.parseFloat(tip_percentage_edit.getText().toString());
        int num_of_people = Integer.parseInt(num_of_people_edit.getText().toString());

        tip_percentage /= 100;

        tip = (total_bill + (total_bill * tip_percentage)) / num_of_people;


        return tip;
    }


}