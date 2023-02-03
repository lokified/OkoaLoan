package com.loki.okoaloan.presentation.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.okoaloan.R
import com.loki.okoaloan.domain.repository.FirebaseAccountRepository
import com.loki.okoaloan.presentation.OkoaLoanViewModel
import com.loki.okoaloan.presentation.navigation.Screens
import com.loki.okoaloan.util.SnackbarManager
import com.loki.okoaloan.util.SnackbarMessage.Companion.toSnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAccount: FirebaseAccountRepository
): OkoaLoanViewModel() {

    val loginFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Email",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    val registerFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Email",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Password",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Confirm Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    fun loginUser(
        email: String,
        password: String,
        openAndPopup: (String, String) -> Unit
    ) {

        launchCatching {
            SnackbarManager.showMessage(R.string.loading)

            delay(1000L)

            firebaseAccount.authenticate(email, password)
            openAndPopup(
                Screens.HomeScreen.route,
                Screens.AuthScreen.route
            )
        }
    }

    fun registerUser(email: String, password: String) {

        launchCatching {
            SnackbarManager.showMessage(R.string.loading)

            delay(1000L)

            firebaseAccount.createAccount(email, password)
            SnackbarManager.showMessage(R.string.register_success)
        }
    }
}