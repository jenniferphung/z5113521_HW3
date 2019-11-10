package com.example.z5113521_hw3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class fAdapter extends RecyclerView.Adapter<fAdapter.ViewHolder> {

    private List<Favourite> mFavourite;
    private Context mContext;

    public fAdapter(Context context, List<Favourite> favourites) {
        mFavourite = favourites;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchrow, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Favourite favourite = mFavourite.get(position);
        String titletext = favourite.getCatName();
        holder.catName.setText(titletext);
    }

    @Override
    public int getItemCount() {
        return mFavourite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView catName;

        public ViewHolder(View itemView) {
            super(itemView);
            catName = (TextView) itemView.findViewById(R.id.CatName);
        }

    }
}