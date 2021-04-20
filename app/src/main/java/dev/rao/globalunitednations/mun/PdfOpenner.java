package dev.rao.globalunitednations.mun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;

import dev.rao.globalunitednations.R;

public class PdfOpenner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_openner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();

        String pdfName = getIntent().getStringExtra("pdf");
        PDFView pdfView = (PDFView) findViewById(R.id.PdfViewer);

        if(pdfName.equals("USA")){
            actionBar.setTitle("UnUSA");
            pdfView.fromAsset("UnUSA.pdf").load();
        }
        else{
            actionBar.setTitle("UN4MUN");
            pdfView.fromAsset("UN4MUN.pdf").load();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}