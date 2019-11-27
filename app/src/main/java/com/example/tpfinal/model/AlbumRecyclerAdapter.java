package com.example.tpfinal.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tpfinal.R;

import java.util.ArrayList;

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView title;
        public TextView artist;
        public TextView genre;
        public TextView nbTrack;

        public ExampleViewHolder(View itemView) {
            super(itemView);
           // mImageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
           artist = itemView.findViewById(R.id.artist);
           genre = itemView.findViewById(R.id.genre);
           nbTrack = itemView.findViewById(R.id.nbTrack);
             mImageView = itemView.findViewById(R.id.imageView);
        }
    }

    public AlbumRecyclerAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageBitmap(currentItem.getImageResource());
        holder.title.setText(currentItem.getTitle());
        holder.artist.setText(currentItem.getArtist());
        holder.genre.setText(currentItem.getGenre());
//      holder.nbTrack.setText(currentItem.getNbTrack());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}