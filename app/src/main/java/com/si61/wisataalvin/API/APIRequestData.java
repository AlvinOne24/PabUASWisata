package com.si61.wisataalvin.API;

import com.si61.wisataalvin.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
        @GET("retrieve.php")
        Call<ModelResponse> ardRetrieve();

        @FormUrlEncoded
        @POST("create.php")
        Call<ModelResponse> ardCreate(
                @Field("nama") String nama,
                @Field("lokasi") String lokasi,
                @Field("urlmap") String urlmap
        );

        @FormUrlEncoded
        @POST("update.php")
        Call<ModelResponse> ardUpdate(
                @Field("id") String id,
                @Field("nama") String nama,
                @Field("lokasi") String lokasi,
                @Field("urlmap") String urlmap
        );

        @FormUrlEncoded
        @POST("delete.php")
        Call<ModelResponse> arddelete(
                @Field("id") String id
        );

}
