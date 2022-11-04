package com.loki.okoaloan.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

class AuthViewModel: ViewModel() {

    val loginFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Phone",
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
                name = "Phone",
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
}