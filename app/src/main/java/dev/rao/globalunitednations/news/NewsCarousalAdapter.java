package dev.rao.globalunitednations.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.model.newsBulletIn;


public class NewsCarousalAdapter extends PagerAdapter {

    private List<newsBulletIn> carousal;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsCarousalAdapter(List<newsBulletIn> carousal, Context context) {
        this.carousal = carousal;
        this.context = context;
    }

    @Override
    public int getCount() {
        return carousal.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_slider, container, false);

        final newsBulletIn details = carousal.get(position);
        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        // desc = view.findViewById(R.id.desc);

        Picasso.get().load(details.getImage()).into(imageView);
        title.setText(details.getTitle());
        //desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baseUrl = "https://news.un.org/en/";
                Intent intent = new Intent(context, DetailNewsActivity.class);
                intent.putExtra("title", details.getTitle());
                intent.putExtra("detailUri", baseUrl + details.getDetailUrl());
                context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}

