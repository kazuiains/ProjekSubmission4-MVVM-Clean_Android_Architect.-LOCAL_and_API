package com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void addFavo(FavoriteTable favoriteTable);

    @Delete
    void delFavo(FavoriteTable favoriteTable);

    @Query("DELETE FROM favorite WHERE type='movie'")
    void deleteAllMovie();

    @Query("DELETE FROM favorite WHERE type='tv'")
    void deleteAllTv();

    @Query("SELECT * FROM favorite WHERE type='movie' ORDER BY number DESC")
    LiveData<List<FavoriteTable>> getFavoriteMovie();

    @Query("SELECT * FROM favorite WHERE type='tv' ORDER BY number DESC")
    LiveData<List<FavoriteTable>> getFavoriteTv();

    @Query("SELECT * FROM favorite WHERE id = :id AND type = :type")
    LiveData<List<FavoriteTable>> checkFav(int id, String type);
}
