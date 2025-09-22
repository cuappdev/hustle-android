package com.cornellappdev.hustle.ui.screens.home

import com.cornellappdev.hustle.data.repository.ExampleRepository
import com.cornellappdev.hustle.ui.HustleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeScreenUiState(
    val property1: Unit = TODO("Specify your Home Screen View State")
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : HustleViewModel<HomeScreenUiState>(HomeScreenUiState()) {
}