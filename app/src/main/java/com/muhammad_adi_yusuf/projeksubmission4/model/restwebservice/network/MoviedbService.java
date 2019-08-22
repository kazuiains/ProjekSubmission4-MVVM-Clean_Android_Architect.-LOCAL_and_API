package com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.network;

import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApiResponse;
import com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.pojo.ApisItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviedbService {

    @GET("movie/upcoming")
    Call<ApiResponse> getMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/{id_item}")
    Call<ApisItem> getDetailMovie(
            @Path("id_item") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("tv/on_the_air")
    Call<ApiResponse> getTvList(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("tv/{id_item}")
    Call<ApisItem> getDetailTv(
            @Path("id_item") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

}
