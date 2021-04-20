package dev.rao.globalunitednations.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.model.NewsSources;


public class NewSourceAdapter extends RecyclerView.Adapter<NewSourceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewsSources> nsList;

    public NewSourceAdapter(Context context1, ArrayList<NewsSources> list){
        context = context1;
        nsList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_sources, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsSources newsSources= nsList.get(position);
        holder.tv_heading.setText(newsSources.getTitle());
        Picasso.get().load(newsSources.getImage()).into(holder.iv_Image);
    }

    @Override
    public int getItemCount() {
        return nsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_heading;
        ImageView iv_Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_heading = itemView.findViewById(R.id.nsHeading);
            iv_Image = itemView.findViewById(R.id.nsImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            NewsSources news = nsList.get(position);

            Intent intent = new Intent(context, DetailNewsActivity.class);
            intent.putExtra("mainUrl", news.getUrl());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
