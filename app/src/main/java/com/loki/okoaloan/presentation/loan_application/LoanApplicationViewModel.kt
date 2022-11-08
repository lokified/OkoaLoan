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

    val personalInfoState = FormState(
        listOf(
            TextFieldState(
                name = "education",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "maritalStatus",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "workStatus",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "loanPurpose",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "incomeStatus",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    val contactInfoState = FormState(
        listOf(
            TextFieldState(
                name = "firstContact",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "firstName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "secondContact",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "secondName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "firstRelationship",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "secondRelationship",
                validators = listOf(
                    Validators.Required()
                )
            ),
        )
    )

}