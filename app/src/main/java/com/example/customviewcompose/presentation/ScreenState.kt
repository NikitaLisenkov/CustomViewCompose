package com.example.customviewcompose.presentation

import com.example.customviewcompose.data.Bar

sealed class ScreenState {
    data object Loading : ScreenState()
    data class Content(val bars: List<Bar>) : ScreenState()
    data class Error(val message: String) : ScreenState()
}
