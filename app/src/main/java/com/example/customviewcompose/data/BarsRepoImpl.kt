package com.example.customviewcompose.data

import com.example.customviewcompose.di.ServiceLocator
import com.example.customviewcompose.domain.BarsRepo

class BarsRepoImpl : BarsRepo {

    val api = ServiceLocator.api

    override suspend fun loadBars(): Result {
        return api.getBars()
    }
}