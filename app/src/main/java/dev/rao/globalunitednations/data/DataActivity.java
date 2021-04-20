package dev.rao.globalunitednations.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.internship.Internship;
import dev.rao.globalunitednations.mun.MunActivity;
import dev.rao.globalunitednations.news.NewsActivity;


public class DataActivity extends AppCompatActivity {

    private String undataUrl = "https://data.un.org/en/";
    private WebView data_webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        data_webView = (WebView) findViewById(R.id.data_webView);
        data_webView.setWebViewClient(new WebViewClient());
        data_webView.loadUrl("https://data.un.org/en/");

        WebSettings settings = data_webView.getSettings();
        settings.setJavaScriptEnabled(true);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_unData);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_news:
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.nav_Internship:
                        startActivity(new Intent(getApplicationContext(), Internship.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.nav_mun:
                        startActivity(new Intent(getApplicationContext(), MunActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;

                    case R.id.nav_unData:
                        return true;
                }
                return false;
            }
        });
    }
}
