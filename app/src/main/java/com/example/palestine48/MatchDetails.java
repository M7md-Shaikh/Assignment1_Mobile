package com.example.palestine48;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MatchDetails extends AppCompatActivity {

    private TextView tvMatchName,tvStadium , tvCity ,tvDate, tvTime, tvCountryInfo;
    private ImageView ivCountryFlag;
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_details);

        tvMatchName=findViewById(R.id.tvMatchName);
        tvStadium=findViewById(R.id.tvStadium);
        tvCity=findViewById(R.id.tvCity);
        tvDate=findViewById(R.id.tvDate);
        tvTime=findViewById(R.id.tvTime);
        tvCountryInfo=findViewById(R.id.tvCountryInfo);
        ivCountryFlag= findViewById(R.id.ivCountryFlag);
        btnBook =findViewById(R.id.btnBook);

        Match match = (Match) getIntent().getSerializableExtra("match");
        if (match != null) {
            tvMatchName.setText(match.getMatchName());
            tvStadium.setText("Stadium: "+match.getStadium());
            tvCity.setText("Destination: "+match.getCityCountry());
            tvDate.setText("Date: "+match.getFormattedDate());
            tvTime.setText("At hour: "+match.getMatchTime());

            // for the country informations
            String country =match.getCityCountry();
            if (country.contains("Jordan")) {
                ivCountryFlag.setImageResource(R.drawable.jordan);
                tvCountryInfo.setText("Jordan - The Hashemite Kingdom of Jordan\n\nCapital:" +
                        " Amman\nRoute: Amman - Petra - Aqaba\nVisa: Palestinians of 1948 do not need a visa\nCurrency:" +
                        " Jordanian Dinar\nTime difference: Same as Palestine time\n");

            } else if (country.contains("Lebanon")) {
                ivCountryFlag.setImageResource(R.drawable.lebanon);
                tvCountryInfo.setText("Lebanon - Lebanese Republic\n" +
                        "\nCapital: Beirut\nVisa: Free visa on arrival\nCurrency: Lebanese Pound");

            } else if (country.contains("Saudi")) {
                ivCountryFlag.setImageResource(R.drawable.saudi);
                tvCountryInfo.setText("Saudi Arabia - Kingdom of Saudi Arabia\n\nCapital:" +
                        " Riyadh\nRoute: Riyadh - Mecca - Jeddah\nVisa: Electronic visa available\nCurrency: Saudi Riyal\n");
            }
        }

        btnBook.setOnClickListener(v -> {
            Intent intent =new Intent(MatchDetails.this, Booking.class);
            intent.putExtra("matchName", match.getMatchName());
            startActivity(intent);
        });
    }
}