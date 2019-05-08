package com.example.appsforgood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvisorHourLogAdapter extends RecyclerView.Adapter<AdvisorHourLogAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<LogEntry> logEntries;
    private Context context;

    public AdvisorHourLogAdapter(Context con, ArrayList<LogEntry> logEntriesList) {
        logEntries = logEntriesList;
        context = con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourlog_listitem, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.d(TAG,"onBindViewHolder: called.");

        Log.d(TAG,"Charity Name: " + logEntries.get(position).getCharityName());
        Log.d(TAG,"Charity Name: " + logEntries.get(position).getStringHours());
        Log.d(TAG,"Charity Name: " + logEntries.get(position).getDate());
        Log.d(TAG,"Charity Name: " + logEntries.get(position).getStringApprovalStatus());

        holder.charity.setText(logEntries.get(position).getCharityName());
        holder.numberOfHours.setText(logEntries.get(position).getStringHours());
        holder.date.setText(logEntries.get(position).getDate());
        holder.approval.setText(logEntries.get(position).getStringApprovalStatus());
    }

    @Override
    public int getItemCount()
    {
        return logEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentLayout;

        TextView charity;
        TextView numberOfHours;
        TextView date;
        TextView approval;

        public ViewHolder(View itemView)
        {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.advisor_hourlog);

            charity = itemView.findViewById(R.id.charity);
            numberOfHours = itemView.findViewById(R.id.number_of_hours);
            date = itemView.findViewById(R.id.date);
            approval = itemView.findViewById(R.id.approval);
        }
    }
}