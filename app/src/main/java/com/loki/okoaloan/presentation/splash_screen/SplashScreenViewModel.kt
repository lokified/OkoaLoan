package com.loki.okoaloan.presentation.splash_screen

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import com.loki.okoaloan.domain.repository.FirebaseAccountRepository
import com.loki.okoaloan.presentation.OkoaLoanViewModel
import com.loki.okoaloan.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val firebaseAccount: FirebaseAccountRepository
) : OkoaLoanViewModel() {

    val showError = mutableStateOf(false)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false

        if (firebaseAccount.hasUser) openAndPopUp(
            Screens.HomeScreen.route,
            Screens.SplashScreen.route
        ) else
            createAnonymousAccount(openAndPopUp)

    }


    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {

        launchCatching(snackbar = false) {
            try {
                firebaseAccount.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true

                throw ex
            }
            openAndPopUp(Screens.GetStartedScreen.route, Screens.SplashScreen.route)
        }
    }
}