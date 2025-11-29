package com.example.palestine48;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Match implements Serializable {

    private String matchName, stadium, cityCountry, matchTime, destInfo;
    private long dateInMillis;

    // Constructure
    public Match(String matchName, String stadium, String cityCountry,
                 long dateInMillis, String matchTime, String destInfo) {
        this.matchName = matchName;
        this.stadium = stadium;
        this.cityCountry = cityCountry;
        this.dateInMillis = dateInMillis;
        this.matchTime = matchTime;
        this.destInfo = destInfo;
    }

    // getters
    public String getMatchName() {
        return matchName;
    }
    public String getStadium() {
        return stadium;
    }


    public String getCityCountry() {
        return cityCountry;
    }
    public long getDateInMillis() {
        return dateInMillis;
    }

    public String getMatchTime() {
        return matchTime;
    }

    // for destenation information
    public String getDestinationInfo() {
        return destInfo != null ? destInfo : "";
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(dateInMillis));
    }
}