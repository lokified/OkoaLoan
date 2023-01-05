package com.loki.okoaloan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loki.okoaloan.presentation.auth_screen.AuthScreen
import com.loki.okoaloan.presentation.change_password.ChangePasswordScreen
import com.loki.okoaloan.presentation.get_started.GetStartedScreen
import com.loki.okoaloan.presentation.home.HomeScreen
import com.loki.okoaloan.presentation.limit_confirmation_screen.LimitProcessingScreen
import com.loki.okoaloan.presentation.loan_application.ContactInfoScreen
import com.loki.okoaloan.presentation.loan_application.LoanApplicationScreen
import com.loki.okoaloan.presentation.loan_application.PersonalInfoScreen
import com.loki.okoaloan.presentation.loan_history.LoanHistoryScreen
import com.loki.okoaloan.presentation.splash_screen.SplashScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.GetStartedScreen.route) {
            GetStartedScreen(navController = navController)

        }

        composable(route = Screens.AuthScreen.route) {
            AuthScreen(navController = navController)
        }

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screens.LoanHistoryScreen.route) {
            LoanHistoryScreen()
        }

        composable(route = Screens.ChangePasswordScreen.route) {
            ChangePasswordScreen()
        }

        composable(route = Screens.LoanApplicationScreen.route) {
            LoanApplicationScreen(navController = navController)
        }

        composable(route = Screens.PersonalInfoScreen.route) {
            PersonalInfoScreen(navController = navController)
        }

        composable(route = Screens.ContactInfoScreen.route) {
            ContactInfoScreen(navController = navController)
        }

        composable(route = Screens.LimitProcessingScreen.route) {
            LimitProcessingScreen(navController = navController)
        }
    }
}


sealed class Screens(val route: String) {

    object SplashScreen: Screens("splash_screen")
    object GetStartedScreen: Screens("get_started_screen")
    object AuthScreen: Screens("auth_screen")
    object HomeScreen: Screens("home_screen")
    object LoanHistoryScreen: Screens("loan_history_screen")
    object ChangePasswordScreen: Screens("change_password_screen")
    object LoanApplicationScreen: Screens("loan_application_screen")
    object PersonalInfoScreen: Screens("personal_info_screen")
    object ContactInfoScreen: Screens("contact_info_screen")
    object LimitProcessingScreen: Screens("limit_processing_screen")
}