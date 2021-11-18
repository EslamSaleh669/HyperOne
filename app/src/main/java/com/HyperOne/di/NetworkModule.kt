package com.HyperOne.di

import android.content.Context
import com.HyperOne.data.network.ApiClient
import com.HyperOne.util.Constants
import com.HyperOne.util.ErrorInterceptor

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule (val context:Context) {


   @Provides
   fun provideOkHttpClient() : OkHttpClient =
       OkHttpClient()
           .newBuilder()
           .writeTimeout(1,TimeUnit.MINUTES)
           .readTimeout(1,TimeUnit.MINUTES)
           .connectTimeout(5,TimeUnit.MINUTES)
           .addInterceptor(ErrorInterceptor())
           .build()

   @Provides
   fun provideApiClient(client:OkHttpClient): ApiClient =
    Retrofit.Builder()
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
           .baseUrl(Constants.BASE_URL2)
           .client(client)
           .build().create(ApiClient::class.java)
}