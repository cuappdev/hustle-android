package com.cornellappdev.hustle.ui.screens.home

import androidx.lifecycle.ViewModel
import com.cornellappdev.hustle.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeScreenUiState(
    val property1: Unit = TODO("Specify your Home Screen View State")
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : ViewModel() {
}