package com.example.revelations.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.revelations.screens.*
import com.example.revelations.viewmodel.RegistrationViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: RegistrationViewModel = viewModel()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("register") { RegistrationScreen(navController, viewModel) }
        composable("events") { EventSelectionScreen(navController, viewModel) }
        composable("confirm") { ConfirmationScreen(viewModel) }
    }
}
