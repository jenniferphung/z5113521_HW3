package com.example.z5113521_hw3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavouriteDAO {
    @Insert
    public void insert(Favourite... favourites);

    @Update
    public void update(Favourite... favourites);

    @Delete
    public void delete(Favourite favourite);

    @Query("SELECT * FROM Favourite")
    public List<Favourite> getFavourites();

    @Query("SELECT * FROM Favourite WHERE catName = :name")
    public Favourite getFavouriteWithName(String name);

}
