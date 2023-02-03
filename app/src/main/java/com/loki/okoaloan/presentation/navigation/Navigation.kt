package com.loki.okoaloan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loki.okoaloan.presentation.OkoaLoanAppState
import com.loki.okoaloan.presentation.auth_screen.AuthScreen
import com.loki.okoaloan.presentation.change_password.ChangePasswordScreen
import com.loki.okoaloan.presentation.get_started.GetStartedScreen
import com.loki.okoaloan.presentation.home.HomeScreen
import com.loki.okoaloan.presentation.limit_confirmation_screen.LimitProcessingScreen
import com.loki.okoaloan.presentation.loan_application.ContactInfoScreen
import com.loki.okoaloan.presentation.loan_application.LoanApplicationScreen
import com.loki.okoaloan.presentation.loan_application.PersonalInfoScreen
import com.loki.okoaloan.presentation.loan_history.LoanHistoryScreen
import com.loki.okoaloan.presentation.rememberAppState
import com.loki.okoaloan.presentation.splash_screen.SplashScreen


@Composable
fun Navigation(
    appState: OkoaLoanAppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.SplashScreen.route
    ) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                openAndPopUp = { route, popup ->
                    appState.navigateAndPopUp(route, popup)
                }
            )
        }

        composable(route = Screens.GetStartedScreen.route) {
            GetStartedScreen { route, popup ->
                appState.navigateAndPopUp(route, popup)
            }
        }

        composable(route = Screens.AuthScreen.route) {
            AuthScreen { route, popup ->
                appState.navigateAndPopUp(route, popup)
            }
        }

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                openScreen = { route ->
                    appState.navigate(route)
                },
                restartApp = { route ->
                    appState.clearAndNavigate(route)
                }
            )
        }

        composable(route = Screens.LoanHistoryScreen.route) {
            LoanHistoryScreen()
        }

        composable(route = Screens.ChangePasswordScreen.route) {
            ChangePasswordScreen()
        }

        composable(route = Screens.LoanApplicationScreen.route) {
            LoanApplicationScreen { route ->
                appState.navigate(route)
            }
        }

        composable(route = Screens.PersonalInfoScreen.route) {
            PersonalInfoScreen { route ->
                appState.navigate(route)
            }
        }

        composable(route = Screens.ContactInfoScreen.route) {
            ContactInfoScreen { route ->
                appState.navigate(route)
            }
        }

        composable(route = Screens.LimitProcessingScreen.route) {
            LimitProcessingScreen { route ->
                appState.clearAndNavigate(route)
            }
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