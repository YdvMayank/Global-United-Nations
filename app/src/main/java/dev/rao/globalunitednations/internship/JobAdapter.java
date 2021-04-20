package dev.rao.globalunitednations.internship;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.model.JobDescription;
import dev.rao.globalunitednations.news.DetailNewsActivity;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private Context context;
    private ArrayList<JobDescription> jobsList;

    public JobAdapter(Context context1, ArrayList<JobDescription> list){
        context = context1;
        jobsList = list;
    }

    @NonNull
    @Override
    public JobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobsdisplayer, parent, false);

        return new JobAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.ViewHolder holder, int position) {
        JobDescription jobList= jobsList.get(position);
        holder.tv_jobTitle.setText(jobList.getName());
        holder.tv_DeadLine.setText("Deadline : " + jobList.getTime());
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_jobTitle;
        TextView tv_DeadLine;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_jobTitle = itemView.findViewById(R.id.jobName);
            tv_DeadLine = itemView.findViewById(R.id.jobDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String url = "https://careers.un.org/lbw/home.aspx?viewtype=SJ&exp=INT&level=0&location=All&occup=0&department=All&bydate=0&occnet=0";
            Intent intent = new Intent(context, DetailNewsActivity.class);
            intent.putExtra("mainUrl", url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}

