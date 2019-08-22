package com.muhammad_adi_yusuf.projeksubmission4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muhammad_adi_yusuf.projeksubmission4.model.localdatabase.pojo.FavoriteTable;
import com.muhammad_adi_yusuf.projeksubmission4.model.repository.LocalRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private LocalRepository repository;
    private LiveData<List<FavoriteTable>> movieData;
    private LiveData<List<FavoriteTable>> tvData;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new LocalRepository(application);
    }

    public void setMov() {
//        if (movieData != null) {
//            return;
//        }
        repository.setMov();
    }

    public void setTv() {
//        if (tvData != null) {
//            return;
//        }
        repository.setTv();
    }

    public void delFav(FavoriteTable fav) {
        repository.delFavorite(fav);
    }

    public void delAllMov() {
        repository.delAllMovie();
    }

    public void delAllTv() {
        repository.delAllTv();
    }

    public LiveData<List<FavoriteTable>> getAllMov() {
        movieData = repository.getMovieFavorite();
        return movieData;
    }

    public LiveData<List<FavoriteTable>> getAllTv() {
        tvData = repository.getTvFavorite();
        return tvData;
    }

}
