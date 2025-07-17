package com.example.customviewcompose.data

import retrofit2.http.GET

interface BarsApi {

    @GET("aggs/ticker/AAPL/range/1/hour/2024-06-09/2025-06-10?adjusted=true&sort=desc&limit=50000&apiKey=c7usvog5w2Bz6PpIbgr1pPA9eQE8encE")
    suspend fun getBars(): Result
}