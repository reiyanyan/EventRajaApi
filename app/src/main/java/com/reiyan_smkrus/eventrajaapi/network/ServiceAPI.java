package com.reiyan_smkrus.eventrajaapi.network;

import com.reiyan_smkrus.eventrajaapi.model.RajaApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    @GET("provinsi")
    Call<RajaApi> getProvinsi ();
    @GET("kabupaten")
    Call<RajaApi> getKabupaten (@Query("idpropinsi") int id );
    @GET("kecamatan")
    Call<RajaApi> getKecamatan(@Query("idkabupaten") int id );
    @GET("kelurahan")
    Call<RajaApi> getKelurahan(@Query("idkecamatan") int id );
}
