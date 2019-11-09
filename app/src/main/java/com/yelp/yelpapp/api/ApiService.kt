package com.yelp.yelpapp.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.yelp.yelpapp.BuildConfig
import com.yelp.yelpapp.model.response.BusinessDetailResponse
import com.yelp.yelpapp.model.response.BusnessSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("businesses/search")
    suspend fun businessSearch(@Header("Authorization") apiKey : String, @Query("latitude") latitude : Double,
                               @Query("longitude") longitude : Double,  @Query("radius") radius : Int) : Response<BusnessSearchResponse>

    @Headers("Content-Type: application/json")
    @GET("businesses/search")
    suspend fun businessSearchByCategory(@Header("Authorization") apiKey : String, @Query("latitude") latitude : Double,
                               @Query("longitude") longitude : Double, @Query("categories") categories : String) : Response<BusnessSearchResponse>

    @Headers("Content-Type: application/json")
    @GET("businesses/{id}")
    suspend fun getBusinessDetails(@Header("Authorization") apiKey : String, @Path("id") id : String) : Response<BusinessDetailResponse>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService{
            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(networkConnectionInterceptor))
                .build().create(ApiService::class.java)
        }

        private fun getOkHttpClient(httpLoggingInterceptor: NetworkConnectionInterceptor): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).build()
        }
    }
}