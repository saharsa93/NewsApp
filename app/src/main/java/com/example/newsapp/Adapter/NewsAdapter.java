package com.example.newsapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Activity.NewsInDetail;
import com.example.newsapp.Parameter.Article;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    Context context;
    List<Article> articles;
    public NewsAdapter(Context context, List<Article> articles){
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Article art = articles.get(position);
        String url = art.getUrl();
        holder.news_title.setText(art.getTitle());
        holder.news_date.setText(art.getPublishedAt());
        String imageUrl = art.getUrlToImage();
        Picasso.get().load(imageUrl).into(holder.news_img);

        holder.news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsInDetail.class);
                intent.putExtra("url", art.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView news_title, news_date;
        ImageView news_img;
        CardView news_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.news_title);
            news_date = itemView.findViewById(R.id.news_date);
            news_img = itemView.findViewById(R.id.news_img);
            news_card = itemView.findViewById(R.id.news_card);
        }
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
