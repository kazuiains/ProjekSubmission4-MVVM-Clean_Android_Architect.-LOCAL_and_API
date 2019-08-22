package com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class FavoriteTable {
    @PrimaryKey(autoGenerate = true)
    private int number;

    private int id;
    private String type;
    private String title;
    private double voteAverage;
    private String release_date;
    private String path;

    public FavoriteTable(int id, String type, String title, double voteAverage, String release_date, String path) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.voteAverage = voteAverage;
        this.release_date = release_date;
        this.path = path;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPath() {
        return path;
    }

}
