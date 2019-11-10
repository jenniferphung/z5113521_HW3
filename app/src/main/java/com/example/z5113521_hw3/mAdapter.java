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

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {

    private ArrayList<Cat> mCat;
    private Context mContext;

    public mAdapter(Context context, ArrayList<Cat> cats) {
        mCat = cats;
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
        final Cat cat = mCat.get(position);
        String titletext = cat.getName();
        holder.catName.setText(titletext);

        holder.catName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, CatActivity.class);
                Bundle b = new Bundle();
                final Cat cat = mCat.get(position);
                b.putString("catId", cat.getId());
                b.putString("catName",cat.getName());
                b.putString("catDesc",cat.getDescription());
                b.putSerializable("catWeight", cat.getWeight());
                b.putString("catTemp", cat.getTemperament());
                b.putString("catOrigin", cat.getOrigin());
                b.putString("catLife", cat.getLife_span());
                b.putString("catWiki", cat.getWikipedia_url());
                b.putInt("catDog", cat.getDog_friendly());
                i.putExtras(b);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView catName;

        public ViewHolder(View itemView) {
            super(itemView);
            catName = (TextView) itemView.findViewById(R.id.CatName);


        }

    }

    public void filterList(ArrayList<Cat> filteredList){
        mCat = filteredList;
        notifyDataSetChanged();
    }
}