package com.example.palestine48;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddMatch extends AppCompatActivity {

    private EditText etMatchName, etStadium, etCityCountry, etMatchTime, etDestinationInfo;
    private TextView tvDate;
    private long selectedDate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_matches);

        etMatchName =findViewById(R.id.etMatchName);
        etStadium =findViewById(R.id.etStadium);
        etCityCountry= findViewById(R.id.etCityCountry);
        etMatchTime= findViewById(R.id.etMatchTime);
        etDestinationInfo= findViewById(R.id.etDestinationInfo);
        tvDate =findViewById(R.id.tvDate);
        Button btnSave =findViewById(R.id.btnSave);

        // DatePicker for add a match/travel
        tvDate.setOnClickListener(v -> {
            Calendar c =Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                Calendar chosen= Calendar.getInstance();
                chosen.set(year, month, day);
                selectedDate= chosen.getTimeInMillis();

                SimpleDateFormat sdf =new SimpleDateFormat("dd MMMM yyyy", new Locale("ar"));
                tvDate.setText(sdf.format(chosen.getTime()));
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });


        btnSave.setOnClickListener(v -> {
            String name=etMatchName.getText().toString().trim();
            String stadium=etStadium.getText().toString().trim();
            String city=etCityCountry.getText().toString().trim();
            String time= etMatchTime.getText().toString().trim();
            String info =etDestinationInfo.getText().toString().trim();

            if (name.isEmpty()|| stadium.isEmpty()||city.isEmpty()||time.isEmpty()|| selectedDate == 0) {
                Toast.makeText(this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<Match> matches= PrefConfig.loadMatches(this);
            if (matches == null) matches= new ArrayList<>();

            Match newMatch= new Match(name, stadium, city, selectedDate, time, info);
            matches.add(0, newMatch);

            PrefConfig.saveMatches(this, matches);

            Toast.makeText(this, "The trip was successfully added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}