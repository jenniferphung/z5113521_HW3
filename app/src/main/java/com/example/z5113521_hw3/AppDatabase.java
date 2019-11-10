package com.example.z5113521_hw3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favourite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavouriteDAO getFavouriteDAO();
}
