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
import dev.rao.globalunitednations.model.newsBulletIn;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<newsBulletIn> newsList;

    public NewsAdapter(Context context1, ArrayList<newsBulletIn> list){
        context = context1;
        newsList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_bulletions_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        newsBulletIn bulletIn= newsList.get(position);
        holder.tv_heading.setText(bulletIn.getTitle());
        Picasso.get().load(bulletIn.getImage()).into(holder.iv_Image);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_heading;
        ImageView iv_Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_heading = itemView.findViewById(R.id.bulletonHeading);
            iv_Image = itemView.findViewById(R.id.bulletonImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            newsBulletIn news = newsList.get(position);
            String baseUrl = "https://news.un.org/en/";

            Intent intent = new Intent(context, DetailNewsActivity.class);
            intent.putExtra("title", news.getTitle());
            intent.putExtra("mainUrl", baseUrl + news.getDetailUrl());
            context.startActivity(intent);
        }
    }
}
