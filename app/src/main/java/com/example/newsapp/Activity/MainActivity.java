package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.newsapp.Adapter.NewsAdapter;
import com.example.newsapp.Api.ApiClient;
import com.example.newsapp.Parameter.Article;
import com.example.newsapp.Parameter.Headlines;
import com.example.newsapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView news_recycler;
    NewsAdapter adapter;
    final String API_KEY = "c7b4fb8e748c4cc5b918a70b0571474a";
    ImageButton reload_btn;
    List<Article> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        news_recycler = findViewById(R.id.news_recycler);
        reload_btn = findViewById(R.id.reload_btn);
        news_recycler.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountry();
        fetchJSON(country, API_KEY);
    }

    private void fetchJSON(String country, String api_key) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, API_KEY);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new NewsAdapter(MainActivity.this, articles);
                    news_recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your Connection...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}