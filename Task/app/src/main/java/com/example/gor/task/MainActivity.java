package com.example.gor.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private List<PhotoItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        new FetchItemTask().execute();
        setupAdapter();
    }

    private void setupAdapter() {
        recyclerView.setAdapter(new PhotoAdapter(list));
    }

    private class PhotoHolder extends RecyclerView {

        private ImageView itemImageView;

        public PhotoHolder(ImageView imageView) {
            super(imageView);
            imageView = imageView.findViewById(R.id.photo_item_id);
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        private List<PhotoItem> photoItems;

        public PhotoAdapter(List<PhotoItem> items) {
            photoItems = items;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View v = inflater.inflate(android.R.layout.photo_item, viewGroup);
            return new PhotoHolder((ImageView) v);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoHolder photoHolder, int i) {
            PhotoItem photoItem = photoItems.get(i);
            Picasso.with(MainActivity.this).load(photoItem.getUrl()).into(photoHolder.itemImageView);

        }

        @Override
        public int getItemCount() {
            return photoItems.size();
        }
    }

    private class FetchItemTask extends AsyncTask<Void, Void, List<PhotoItem>> {

        @Override
        protected List<PhotoItem> doInBackground(Void... voids) {
            return new FlickFetcher().fatchItem();
        }

        @Override
        protected void onPostExecute(List<PhotoItem> items) {
            list = items;
            setupAdapter();
        }
    }
}
