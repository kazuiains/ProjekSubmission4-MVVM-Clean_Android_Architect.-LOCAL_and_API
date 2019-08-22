package com.muhammad_adi_yusuf.projeksubmission4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muhammad_adi_yusuf.projeksubmission4.model.repository.ApiRepository;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ApisItem>> movieList;
    private MutableLiveData<List<ApisItem>> tvList;

    public void setMovie(String laNguage) {
        if (movieList != null) {
            return;
        }
        ApiRepository repoMovie = ApiRepository.getApiRepo();
        movieList = new MutableLiveData<>();
        movieList = repoMovie.getMovieList(laNguage);
    }

    public LiveData<List<ApisItem>> getMovie() {
        return movieList;
    }

    public void setTv(String laNguage) {
        if (tvList != null) {
            return;
        }
        ApiRepository repoTv = ApiRepository.getApiRepo();
        tvList = new MutableLiveData<>();
        tvList = repoTv.getTvList(laNguage);
    }

    public LiveData<List<ApisItem>> getTv() {
        return tvList;
    }
}
