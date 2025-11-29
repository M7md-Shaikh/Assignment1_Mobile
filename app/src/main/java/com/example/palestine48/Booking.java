package com.example.palestine48;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Booking extends AppCompatActivity {

    private TextView tvMatchName, tvDepartureDate, tvEarlyDateLabel, tvEarlyDeparture;
    private EditText etFullName, etIDNumber;
    private Switch switchEarlyTrip;
    private RadioGroup rgSeatType;
    private CheckBox cbHotel;
    private Button btnConfirmBooking;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        // ربط العناصر
        tvMatchName =findViewById(R.id.tvMatchName);
        tvDepartureDate= findViewById(R.id.tvDepartureDate);
        etFullName =findViewById(R.id.etFullName);
        etIDNumber =findViewById(R.id.etIDNumber);
        switchEarlyTrip= findViewById(R.id.switchEarlyTrip);
        tvEarlyDateLabel =findViewById(R.id.tvEarlyDateLabel);
        tvEarlyDeparture =findViewById(R.id.tvEarlyDeparture);
        rgSeatType= findViewById(R.id.rgSeatType);
        cbHotel= findViewById(R.id.cbHotel);
        btnConfirmBooking =findViewById(R.id.btnConfirmBooking);

        Match match=(Match) getIntent().getSerializableExtra("match");
        if (match != null) {
            tvMatchName.setText(match.getMatchName());

            // the date of the travel
            Calendar cal =Calendar.getInstance();
            cal.setTimeInMillis(match.getDateInMillis());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            tvDepartureDate.setText("Take-off date: " +cal.get(Calendar.DAY_OF_MONTH) + "/"
                    + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
        }

        // when the switch is true : the datePacker is show
        switchEarlyTrip.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvEarlyDateLabel.setVisibility(TextView.VISIBLE);
                tvEarlyDeparture.setVisibility(TextView.VISIBLE);
            } else {
                tvEarlyDateLabel.setVisibility(TextView.GONE);
                tvEarlyDeparture.setVisibility(TextView.GONE);
                tvEarlyDeparture.setText("Click to select an early travel date");            }
        });

        // DatePicker for early travel
        tvEarlyDeparture.setOnClickListener(v -> {
            Calendar c =Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                String[] months ={"January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"};
                tvEarlyDeparture.setText(day + " " + months[month]+" " + year);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });


        btnConfirmBooking.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String id = etIDNumber.getText().toString().trim();

            if (name.isEmpty()||id.isEmpty()) {
                Toast.makeText(this, "Please enter name and ID number", Toast.LENGTH_SHORT).show();                return;
            }

            String seat=(rgSeatType.getCheckedRadioButtonId() ==R.id.rbVIP) ? "VIP" : "Regular";
            String extras= cbHotel.isChecked() ? ", Hotel Booking" : "";
            String message="Booking successful!\n" +
                    "Name: "+name + "\n" +
                    "ID: " +id+"\n" +
                    "Seat: " +seat+extras;

            if (switchEarlyTrip.isChecked()) {
                message += "\nEarly Departure: "+tvEarlyDeparture.getText();            }

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}