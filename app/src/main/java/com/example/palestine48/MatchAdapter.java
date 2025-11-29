package com.example.palestine48;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchVH> {

    private ArrayList<Match> matches;
    private OnMatchClickListener listener;

    public interface OnMatchClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnMatchClickListener(OnMatchClickListener l) {
        this.listener =l;
    }

    public MatchAdapter(ArrayList<Match> list) {
        this.matches= list;
    }

    @NonNull
    @Override
    public MatchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new MatchVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchVH holder, int position) {
        Match m =matches.get(position);
        holder.tvMatchName.setText(m.getMatchName());
        holder.tvStadium.setText(m.getStadium());
        holder.tvCityCountry.setText(m.getCityCountry());
        holder.tvDate.setText(m.getFormattedDate());
        holder.tvTime.setText(m.getMatchTime());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    static class MatchVH extends RecyclerView.ViewHolder {
        TextView tvMatchName, tvStadium, tvCityCountry, tvDate, tvTime;

        public MatchVH(@NonNull View itemView) {
            super(itemView);
            tvMatchName =itemView.findViewById(R.id.tvMatchName);
            tvStadium= itemView.findViewById(R.id.tvStadium);
            tvCityCountry =itemView.findViewById(R.id.tvCityCountry);
            tvDate =itemView.findViewById(R.id.tvDate);
            tvTime =itemView.findViewById(R.id.tvTime);
        }
    }
}
