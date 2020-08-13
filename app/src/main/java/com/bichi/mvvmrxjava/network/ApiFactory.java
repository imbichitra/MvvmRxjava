package com.bichi.mvvmrxjava.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bichi.mvvmrxjava.utils.Constants.BASE_URL;


public class ApiFactory {
    private static Retrofit retrofit;
    public static UserService create() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(UserService.class);
    }
}
