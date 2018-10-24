package com.reiyan_smkrus.eventrajaapi.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ClientAPI {
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "https://x.rajaapi.com/MeP7c5neKUAa4p2czb3gCW9u9QVAV2hki89kEZUd4gocpKYJqLVKwCLl7y/m/wilayah/";
    public static Retrofit getRetrofitClient(){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
