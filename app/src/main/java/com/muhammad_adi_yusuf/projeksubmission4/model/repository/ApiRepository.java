package com.muhammad_adi_yusuf.projeksubmission4.model.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.network.ConfigRetrofit;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.network.MoviedbService;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApiResponse;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.muhammad_adi_yusuf.projeksubmission4.BuildConfig.DBAPIKEY;

public class ApiRepository {

    private MoviedbService apiData;
    private static ApiRepository newRepo;
    private MutableLiveData<List<ApisItem>> moVie;
    private MutableLiveData<List<ApisItem>> tvSeries;

    public static ApiRepository getApiRepo() {
        if (newRepo == null) {
            newRepo = new ApiRepository();
        }
        return newRepo;
    }

    private ApiRepository() {
        apiData = ConfigRetrofit.serVice(MoviedbService.class);
    }

    //Get ALl Movie Data
    public MutableLiveData<List<ApisItem>> getMovieList(String laNguage) {

        moVie = new MutableLiveData<>();
        apiData.getMovieList(DBAPIKEY, laNguage).enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse iTem = response.body();
                    assert iTem != null;
                    moVie.postValue(iTem.getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                moVie.postValue(null);
            }
        });
        return moVie;
    }

    //Get All Tv Data
    public MutableLiveData<List<ApisItem>> getTvList(String laNguage) {

        tvSeries = new MutableLiveData<>();
        apiData.getTvList(DBAPIKEY, laNguage).enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse iTem = response.body();
                    assert iTem != null;
                    tvSeries.postValue(iTem.getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                tvSeries.postValue(null);
            }
        });
        return tvSeries;
    }

    //Get Movie Detail
    public MutableLiveData<List<ApisItem>> getMovieDetail(String laNguage, int idItem) {

        moVie = new MutableLiveData<>();
        apiData.getDetailMovie(idItem, DBAPIKEY, laNguage).enqueue(new Callback<ApisItem>() {

            @Override
            public void onResponse(@NonNull Call<ApisItem> call, @NonNull Response<ApisItem> response) {
                if (response.isSuccessful()) {
                    moVie.postValue(Collections.singletonList(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApisItem> call, @NonNull Throwable t) {
                moVie.postValue(null);
            }
        });
        return moVie;
    }

    //Get Tv Detail
    public MutableLiveData<List<ApisItem>> getTvDetail(String laNguage, int idItem) {

        tvSeries = new MutableLiveData<>();
        apiData.getDetailTv(idItem, DBAPIKEY, laNguage).enqueue(new Callback<ApisItem>() {

            @Override
            public void onResponse(@NonNull Call<ApisItem> call, @NonNull Response<ApisItem> response) {
                if (response.isSuccessful()) {
                    tvSeries.postValue(Collections.singletonList(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApisItem> call, @NonNull Throwable t) {
                tvSeries.postValue(null);
            }
        });
        return tvSeries;
    }
}
