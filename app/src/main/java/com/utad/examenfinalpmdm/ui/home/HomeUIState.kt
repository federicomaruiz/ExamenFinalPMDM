package com.utad.examenfinalpmdm.ui.home

import com.utad.examenfinalpmdm.data.network.model.responseItem

data class HomeUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val schoolList: List<responseItem>? = null
)
