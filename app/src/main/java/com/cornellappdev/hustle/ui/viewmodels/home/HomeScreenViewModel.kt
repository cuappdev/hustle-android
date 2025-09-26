package com.cornellappdev.hustle.ui.viewmodels.home

import com.cornellappdev.hustle.data.repository.ExampleRepository
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeScreenUiState(
    val exampleData: String = "Example Data"
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : HustleViewModel<HomeScreenUiState>(HomeScreenUiState()) {
}