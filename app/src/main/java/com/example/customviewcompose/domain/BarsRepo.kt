package com.example.customviewcompose.domain

import com.example.customviewcompose.data.Result

interface BarsRepo {

    suspend fun loadBars(): Result
}