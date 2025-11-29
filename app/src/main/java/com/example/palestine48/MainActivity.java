package com.example.palestine48;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private ArrayList<Match> allMatches;
    private ArrayList<Match> filteredList;

    // life cycle methods
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "MainActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        allMatches = PrefConfig.loadMatches(this);
        filteredList.clear();
        filteredList.addAll(allMatches);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "MainActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "MainActivity: onStop");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        allMatches =PrefConfig.loadMatches(this);
        filteredList= new ArrayList<>(allMatches);
        adapter =new MatchAdapter(filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


// when we click on card view , the match details is shows
        adapter.setOnMatchClickListener(new MatchAdapter.OnMatchClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent =new Intent(MainActivity.this, MatchDetails.class);
                intent.putExtra("match", filteredList.get(position));
                startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {}
        });


// Add match/trip button
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent= new Intent(MainActivity.this, AddMatch.class);
            startActivity(intent);
        });

// for the search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList.clear();
                if (newText.isEmpty()) {
                    filteredList.addAll(allMatches);
                } else {
                    String query =newText.toLowerCase();
                    for (Match m : allMatches) {
                        if (m.getMatchName().toLowerCase().contains(query) ||
                                m.getCityCountry().toLowerCase().contains(query)) {
                            filteredList.add(m);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList.clear();
                if (newText.isEmpty()) {
                    filteredList.addAll(allMatches);
                } else {
                    String query =newText.toLowerCase();
                    for (Match m : allMatches) {
                        if (m.getMatchName().toLowerCase().contains(query) ||
                                m.getCityCountry().toLowerCase().contains(query)) {
                            filteredList.add(m);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}