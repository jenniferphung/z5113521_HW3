package com.example.z5113521_hw3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favourite")
public class Favourite {

    @PrimaryKey
    @NonNull
    public String catName;

    public Favourite() {
    }

    public Favourite(@NonNull String catName) {
        this.catName = catName;
    }

    @NonNull
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
