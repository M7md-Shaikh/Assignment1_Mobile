package com.example.palestine48;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class PrefConfig {
    private static final String MY_PREF = "palestine48_prefs";
    private static final String KEY_LIST = "matches_list";

    public static void saveMatches(Context context, ArrayList<Match> list) {
        SharedPreferences prefs =context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson =new Gson();
        String json= gson.toJson(list);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    public static ArrayList<Match> loadMatches(Context context) {
        SharedPreferences prefs= context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        Gson gson =new Gson();
        String json= prefs.getString(KEY_LIST, null);

        Type type =new TypeToken<ArrayList<Match>>(){}.getType();
        ArrayList<Match> list= gson.fromJson(json, type);

        if (list == null||list.isEmpty()) {
            list = new ArrayList<>();
            Calendar cal = Calendar.getInstance();

            cal.set(2026, Calendar.MARCH, 15);
            list.add(new Match(
                    "Palestine vs Jordan",
                    "Amman International Stadium",
                    "Amman - Jordan",
                    cal.getTimeInMillis(),
                    "20:30",
                    "Visa not required\nCurrency: Jordanian Dinar\n"
            ));

            cal.set(2026, Calendar.APRIL, 5);
            list.add(new Match(
                    "Palestine vs Lebanon",
                    "Camille Chamoun Stadium",
                    "Beirut - Lebanon",
                    cal.getTimeInMillis(),
                    "20:00",
                    "Free visa on arrival\n" +
                            "Currency: Lebanese Pound"
            ));

            cal.set(2026, Calendar.MAY, 20);
            list.add(new Match(
                    "Palestine vs Saudi Arabia",
                    "King Fahd Stadium",
                    "Riyadh - Saudi Arabia",
                    cal.getTimeInMillis(),
                    "21:15",
                    "e-Visa\nCurrency: Saudi Riyal\n"
            ));
            saveMatches(context, list);
        }
        return list;
    }
}