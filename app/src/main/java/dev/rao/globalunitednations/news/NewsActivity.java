package dev.rao.globalunitednations.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.data.DataActivity;
import dev.rao.globalunitednations.internship.Internship;
import dev.rao.globalunitednations.model.NewsSources;

import dev.rao.globalunitednations.model.newsBulletIn;
import dev.rao.globalunitednations.mun.MunActivity;


public class NewsActivity extends AppCompatActivity {

    ViewPager viewPager;
    NewsCarousalAdapter carousalAdapter;
    TabLayout indicator;
    private RecyclerView recyclerView, rvNewSources;
    private NewsAdapter adapter;
    private ArrayList<newsBulletIn> parseItems = new ArrayList<>();
    private ArrayList<newsBulletIn> newscarousal = new ArrayList<>();
    private ProgressBar progressBar;
    private ArrayList<NewsSources> nsList;
    private BottomNavigationView bottomNavigationView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");

        progressBar = findViewById(R.id.progessBar);
        recyclerView = findViewById(R.id.Rv_newsBulletion);
        carousalAdapter = new NewsCarousalAdapter(newscarousal, this);


        Content content = new Content();
        content.execute();
        initNewsRecyclerView();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bar);
        initBottomNavBar();

        rvNewSources = findViewById(R.id.rv_newsSorces);
        initRecyclerView();
    }


    private void initBottomNavBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        return true;

                    case R.id.nav_Internship:
                        startActivity(new Intent(getApplicationContext(), Internship.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_mun:
                        startActivity(new Intent(getApplicationContext(), MunActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_unData:
                        startActivity(new Intent(getApplicationContext(), DataActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    private void initNewsRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this, parseItems);
        recyclerView.setAdapter(adapter);

        indicator = (TabLayout) findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(carousalAdapter);
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }

    private void initRecyclerView() {
        nsList = new ArrayList<>();

        nsList.add(new NewsSources("Reuters", R.drawable.reuters, "https://in.reuters.com/"));
        nsList.add(new NewsSources("United Nations", R.drawable.logo_un, "https://news.un.org/en/"));

        NewSourceAdapter adapter = new NewSourceAdapter(getApplicationContext(), nsList);
        rvNewSources.setLayoutManager(new LinearLayoutManager(this));
        rvNewSources.setAdapter(adapter);
    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            NewsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < newscarousal.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
            //progressBar.setVisibility(View.VISIBLE);
            carousalAdapter.notifyDataSetChanged();
            // progressBar.startAnimation(AnimationUtils.loadAnimation(NewsActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //progressBar.setVisibility(View.GONE);
            // progressBar.startAnimation(AnimationUtils.loadAnimation(NewsActivity.this, android.R.anim.fade_out));
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
            carousalAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String url = "https://news.un.org/en/";

            try {
                final Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.view-content");
                int size = data.size();
                data.remove(0);
                for (int i = 1; i < 10; i++) {
                    String title = data.select("div.body-wrapper")
                            .select("h1.story-title")
                            .select("a")
                            .eq(i)
                            .text();

                    String imgUrl = data.select("img")
                            .eq(i)
                            .attr("src");


                    String detailUrl = data.select("div.body-wrapper")
                            .select("h1.story-title")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    if (i < 5) {
                        newscarousal.add(new newsBulletIn(title, imgUrl, detailUrl));
                        Log.d("message", "Hrllo                        ");
                    } else {
                        parseItems.add(new newsBulletIn(title, imgUrl, detailUrl));
                    }

                    Log.d("deeeee", "welcome" + title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
