package com.muhammad_adi_yusuf.projeksubmission4.model.restwebservice.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.muhammad_adi_yusuf.projeksubmission4.BuildConfig.BASE_URL;

public class ConfigRetrofit {
    private static Retrofit retRofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S serVice(Class<S> serviceClass) {
        return retRofit.create(serviceClass);
    }
}
