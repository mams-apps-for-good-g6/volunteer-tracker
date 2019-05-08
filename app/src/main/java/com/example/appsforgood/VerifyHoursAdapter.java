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

public class VerifyHoursAdapter extends RecyclerView.Adapter<VerifyHoursAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<LogEntry> verifyLogsList;
    private Context context;

    public VerifyHoursAdapter(Context con, ArrayList<LogEntry> hoursThatNeedToBeVerified) {
        verifyLogsList = hoursThatNeedToBeVerified;
        context = con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verify_listitem, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.student_name.setText(verifyLogsList.get(position).getVolunteerName());
        holder.charity_name.setText(verifyLogsList.get(position).getCharityName());
        holder.hours_served.setText(Double.toString(verifyLogsList.get(position).getHours()));
    }

    @Override
    public int getItemCount()
    {
        return verifyLogsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout verifyHoursRecyclerView;

        TextView student_name;
        TextView charity_name;
        TextView hours_served;

        public ViewHolder(View itemView)
        {
            super(itemView);
            verifyHoursRecyclerView = itemView.findViewById(R.id.verify_hours_recycler_view);

            student_name = itemView.findViewById(R.id.student_name);
            charity_name = itemView.findViewById(R.id.charity_name);
            hours_served = itemView.findViewById(R.id.hours_served);
        }
    }
}