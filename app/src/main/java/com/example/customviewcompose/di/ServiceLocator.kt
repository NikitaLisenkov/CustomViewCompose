package com.example.customviewcompose.di

import com.example.customviewcompose.data.BarsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceLocator {

    private const val BASE_URL = "https://api.polygon.io/v2/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: BarsApi = retrofit.create()
}