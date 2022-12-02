package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toolbar;

public class Internet_View_News extends AppCompatActivity {

    WebView view_Web;
    Toolbar bar;
    ImageView go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_view_news);
        getSupportActionBar().hide();
        //bar =findViewById(R.id.tool_bar11);
        go_back = findViewById(R.id.back_red2);
        view_Web = findViewById(R.id.view1_web);

        Intent intent2 = getIntent();
        String url = intent2.getStringExtra("url");
        view_Web.setWebViewClient(new WebViewClient());
        view_Web.loadUrl(url);


        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_red =new Intent(Internet_View_News.this, Heading_News.class);
                startActivity(intent_red);
            }
        });
    }
}