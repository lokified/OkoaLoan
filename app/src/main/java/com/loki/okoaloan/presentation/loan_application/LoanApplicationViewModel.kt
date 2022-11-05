package com.loki.okoaloan.presentation.loan_application

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.*

class LoanApplicationViewModel (): ViewModel() {

    val basicInfoState = FormState(

        fields = listOf(
            TextFieldState(
                name = "firstName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "lastName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "email",
                validators = listOf(
                    Validators.Required(),
                    Validators.Email()
                )
            ),
            TextFieldState(
                name = "id",
                validators = listOf(
                    Validators.Required()
                )
            ),
            ChoiceState(
                name = "gender",
                validators = listOf(Validators.Required())
            )
        )
    )

}