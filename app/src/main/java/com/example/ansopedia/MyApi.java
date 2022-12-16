package com.example.ansopedia;

import com.example.ansopedia.models.CategoriesModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    @GET("?groupId=2")
    Call<List<CategoriesModel>> getModels();
}
