package com.yani.api;

import com.yani.content.Musician;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface APIService {

    @GET("download.cdn.yandex.net/mobilization-2016/artists.json")
    Call<List<Musician>> getListOfMusicians();

}
