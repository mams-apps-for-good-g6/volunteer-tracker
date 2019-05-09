package com.example.appsforgood;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Volunteer> studentList;
    private ArrayList<LogEntry> logEntries;
    private Context context;
    private String orgPath;
    private int index;

    public StudentListAdapter(Context con, ArrayList<Volunteer> listOfStudents, String path) {
        studentList = listOfStudents;
        context = con;
        orgPath = path;
        index = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_listitem, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Log.d(TAG,"onBindViewHolder: called.");

        Log.d(TAG,"Volunteer Name: " + studentList.get(position).getFullName());
        Log.d(TAG,"Total Hours: " + studentList.get(position).getTotalHours());

        holder.student_name.setText(studentList.get(position).getFullName());
        holder.student_totalhours.setText(Double.toString(studentList.get(position).getTotalHours()));

        holder.student_listitem.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Volunteer vol = studentList.get(holder.getAdapterPosition());
            logEntries = vol.getLogEntries();
            index = vol.getIndex();

            Log.d("Megan", "Sending info about volunteer if clicked: " + vol.getFullName() + ". Index: " + Integer.toString(index));

            Intent intent = new Intent(context, AdvisorHourLogRecyclerView.class);
            intent.putParcelableArrayListExtra("logEntries", logEntries);
            intent.putExtra("orgPath", orgPath);
            intent.putExtra("index", index);
            intent.putExtra("volName", vol.getFullName());
            context.startActivity(intent);
        }
    });
    }
    @Override
    public int getItemCount()
    {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout studentListRecyclerView;

        TextView student_name;
        TextView student_totalhours;
        ConstraintLayout student_listitem;

        public ViewHolder(View itemView)
        {
            super(itemView);
            studentListRecyclerView = itemView.findViewById(R.id.student_list_recycler_view);
            student_name = itemView.findViewById(R.id.student_name);
            student_totalhours = itemView.findViewById(R.id.student_totalhours);
            student_listitem = itemView.findViewById(R.id.student_listitem);
        }
    }
}