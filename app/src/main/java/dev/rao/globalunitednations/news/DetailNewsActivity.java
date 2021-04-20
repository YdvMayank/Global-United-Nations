package dev.rao.globalunitednations.news;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dev.rao.globalunitednations.R;


public class DetailNewsActivity extends AppCompatActivity {

    private WebView webView;
    private String title, mainUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mainUrl = getIntent().getStringExtra("mainUrl");
        actionBar.setTitle(getIntent().getStringExtra("title"));

        webView = (WebView) findViewById(R.id.detail_webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mainUrl);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
