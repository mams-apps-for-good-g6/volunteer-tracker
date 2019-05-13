package com.example.appsforgood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.student_name.setText(verifyLogsList.get(position).getVolunteerName());
        holder.charity_name.setText(verifyLogsList.get(position).getCharityName());
        holder.hours_served.setText(Double.toString(verifyLogsList.get(position).getHours()));

        holder.verify_button.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            LogEntry log = verifyLogsList.get(holder.getAdapterPosition());
            log.setApprovalStatus(1);
            verifyLogsList.remove(holder.getAdapterPosition());
            Toast.makeText(context, log.getVolunteerName() + "'s hours have been accepted. Return to your profile to see the changes.",Toast.LENGTH_LONG).show();

            //Send it to firebase

            Log.d("Megan ", "LogEntry path: organizations/" + log.getPath());

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("organizations/" + log.getPath());
            ref.setValue(log);
        }
    });

        holder.decline_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LogEntry log = verifyLogsList.get(holder.getAdapterPosition());
                log.setApprovalStatus(-1);
                verifyLogsList.remove(holder.getAdapterPosition());
                Toast.makeText(context, log.getVolunteerName() + "'s hours have been declined. Return to your profile to see the changes.",Toast.LENGTH_LONG).show();

                //Send it to firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("organizations/" + log.getPath());
                ref.setValue(log);
            }
        });
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
        Button verify_button;
        Button decline_button;

        public ViewHolder(View itemView)
        {
            super(itemView);
            verifyHoursRecyclerView = itemView.findViewById(R.id.verify_hours_recycler_view);

            student_name = itemView.findViewById(R.id.student_name);
            charity_name = itemView.findViewById(R.id.charity_name);
            hours_served = itemView.findViewById(R.id.hours_served);
            verify_button = itemView.findViewById(R.id.verify_button);
            decline_button = itemView.findViewById(R.id.decline_button);
        }
    }
}