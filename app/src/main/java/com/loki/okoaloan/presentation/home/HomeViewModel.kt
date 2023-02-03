package com.loki.okoaloan.presentation.home

import com.loki.okoaloan.domain.repository.FirebaseAccountRepository
import com.loki.okoaloan.presentation.OkoaLoanViewModel
import com.loki.okoaloan.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseAccount: FirebaseAccountRepository
): OkoaLoanViewModel() {


    fun onLogOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            firebaseAccount.signOut()
            restartApp(Screens.SplashScreen.route)
        }
    }
}