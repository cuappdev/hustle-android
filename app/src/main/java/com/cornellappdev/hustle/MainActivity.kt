package com.cornellappdev.hustle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cornellappdev.hustle.ui.HustleApp
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.ui.viewmodels.RootViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val rootViewModel: RootViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            rootViewModel.uiStateFlow.value.isLoading
        }

        enableEdgeToEdge()
        setContent {
            HustleTheme {
                val rootUiState = rootViewModel.collectUiStateValue()
                if (!rootUiState.isLoading) {
                    HustleApp(isSignedIn = rootUiState.isSignedIn)
                }
            }
        }
    }
}