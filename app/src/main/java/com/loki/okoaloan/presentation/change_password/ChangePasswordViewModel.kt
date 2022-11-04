package com.loki.okoaloan.presentation.change_password

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators

class ChangePasswordViewModel: ViewModel() {

    val formState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Old Password",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "New Password",
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