package com.example.newsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapp.R;

public class NewsInDetail extends AppCompatActivity {

    WebView news_webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_in_detail);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        news_webView = findViewById(R.id.news_webView);
        news_webView.getSettings().setDomStorageEnabled(true);
        news_webView.getSettings().setJavaScriptEnabled(true);
        news_webView.getSettings().setLoadsImagesAutomatically(true);
        news_webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        news_webView.setWebViewClient(new WebViewClient());
        news_webView.loadUrl(url);
    }
}
