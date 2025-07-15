package com.example.customviewcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customviewcompose.data.BarsRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class BarsViewModel : ViewModel() {

    private val repo = BarsRepoImpl()

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    init {
        fetchBars()
    }

    private fun fetchBars() {
        viewModelScope.launch {
            _state.value = ScreenState.Loading
            try {
                runCatching {
                    repo.loadBars().barList
                }.onSuccess { bars ->
                    _state.value = ScreenState.Content(bars)
                }.onFailure { e ->
                    _state.value = ScreenState.Error(e.localizedMessage ?: "Произошла ошибка")
                }
            } catch (e: CancellationException) {
                throw e
            }
        }

    }
}