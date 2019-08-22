package com.muhammad_adi_yusuf.projeksubmission4.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;
import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.room.FavoriteDao;
import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.room.MoviedbDatabase;

import java.util.List;

public class LocalRepository {
    private FavoriteDao favDao;
    private LiveData<List<FavoriteTable>> movieFavorite;
    private LiveData<List<FavoriteTable>> tvFavorite;
    private LiveData<List<FavoriteTable>> checkFavorite;

    public LocalRepository(Application application) {
        MoviedbDatabase database = MoviedbDatabase.getMoviedb(application);
        favDao = database.favDao();
    }

    public void checkFav(int id, String type) {
        checkFavorite = new MutableLiveData<>();
        checkFavorite = favDao.checkFav(id, type);
    }

    public void setMov() {
        movieFavorite = new MutableLiveData<>();
        movieFavorite = favDao.getFavoriteMovie();
    }


    public void setTv() {
        tvFavorite = new MutableLiveData<>();
        tvFavorite = favDao.getFavoriteTv();
    }

    public void addFavorite(FavoriteTable fav) {
        new addFav(favDao).execute(fav);
    }

    public void delFavorite(FavoriteTable fav) {
        new delFav(favDao).execute(fav);
    }

    public void delAllMovie() {
        new delAllMovie(favDao).execute();
    }

    public void delAllTv() {
        new delAllTv(favDao).execute();
    }

    public LiveData<List<FavoriteTable>> getMovieFavorite() {
        return movieFavorite;
    }

    public LiveData<List<FavoriteTable>> getTvFavorite() {
        return tvFavorite;
    }

    public LiveData<List<FavoriteTable>> dataCheck() {
        return checkFavorite;
    }


    private static class addFav extends AsyncTask<FavoriteTable, Void, Void> {
        private FavoriteDao dao;

        private addFav(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteTable... table) {
            dao.addFavo(table[0]);
            return null;
        }

    }

    private static class delFav extends AsyncTask<FavoriteTable, Void, Void> {
        private FavoriteDao dao;

        private delFav(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteTable... table) {
            dao.delFavo(table[0]);
            return null;
        }

    }

    private static class delAllMovie extends AsyncTask<Void, Void, Void> {
        private FavoriteDao dao;

        private delAllMovie(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllMovie();
            return null;
        }

    }

    private static class delAllTv extends AsyncTask<Void, Void, Void> {
        private FavoriteDao dao;

        private delAllTv(FavoriteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllTv();
            return null;
        }

    }

}
