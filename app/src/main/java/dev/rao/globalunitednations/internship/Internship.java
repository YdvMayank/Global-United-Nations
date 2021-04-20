package dev.rao.globalunitednations.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.data.DataActivity;
import dev.rao.globalunitednations.model.JobDescription;
import dev.rao.globalunitednations.mun.MunActivity;
import dev.rao.globalunitednations.news.NewsActivity;

public class Internship extends AppCompatActivity {

    private ArrayList<JobDescription> jobsList = new ArrayList<>();
    private ProgressBar progressBar;
    private JobAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.intern_RecyclerView);
        progressBar = (ProgressBar) findViewById(R.id.internProgressBar);
        Context context = new Context();
        context.execute();

        adapter = new JobAdapter(getApplicationContext(), jobsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_Internship);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_mun:
                        startActivity(new Intent(getApplicationContext(), MunActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.nav_Internship:
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

    private class Context extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Internship.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void Void) {
            super.onPostExecute(Void);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Internship.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://careers.un.org/lbw/home.aspx?viewtype=SJ&exp=INT&level=0&location=All&occup=0&department=All&bydate=0&occnet=0";

                Document document = Jsoup.connect(url).get();
                Elements data = document.select("table.sch-grid-standard tr");
                int size = data.size();
                int i = 0;

                for (Element row : document.select(
                        "table.sch-grid-standard tr")) {
                    if (i < size - 2) {
                        i++;
                        if (row.select("td:nth-of-type(1)").text().equals("")) {
                            continue;
                        } else {
                            final String title = row.select("td:nth-of-type(1)").text();
                            final String detailUrl = row.select("td:nth-of-type(1)").attr("href");
                            final String deadline = row.select("td:nth-of-type(8)").text();
                            String title2 = title.replace(" [Temporary]", "");
                            title2 = title2.replace("INTERN - ", "");
                            title2 = title2.replace("Internship - ", "");
                            title2 = title2.replace("Intern - ", "");

                            jobsList.add(new JobDescription(title2, deadline));

                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

    }
}