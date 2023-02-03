package com.loki.okoaloan.presentation.loan_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.navigation.Screens
import com.loki.okoaloan.presentation.common.*

@Composable
fun ContactInfoScreen(
    openScreen: (String) -> Unit
) {

    Scaffold(topBar = { TopBar(title = "Personal Information") }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ContactInfoForm(
                modifier = Modifier.padding(16.dp),
                openScreen = openScreen
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactInfoForm(
    modifier: Modifier = Modifier,
    openScreen: (String) -> Unit
) {

    val viewModel = LoanApplicationViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.contactInfoState }

    val firstContact = formState.getState<TextFieldState>("firstContact")
    val secondContact = formState.getState<TextFieldState>("secondContact")
    val firstName = formState.getState<TextFieldState>("firstName")
    val secondName = formState.getState<TextFieldState>("secondName")
    val firstRelationship = formState.getState<TextFieldState>("firstRelationship")
    val secondRelationship = formState.getState<TextFieldState>("secondRelationship")
    val relationshipOptions = listOf("sibling", "mother", "father", "guardian")

    Column(modifier = modifier) {

        InputLabel(label = "First Contact")
        AltInput(
            placeholder = "Enter name",
            value = firstName.value,
            onValueChange = { firstName.change(it) },
            errorMessage = firstName.errorMessage,
            isError = firstName.hasError
        )
        AltInput(
            placeholder = "Enter contact",
            value = firstContact.value,
            onValueChange = { firstContact.change(it) },
            errorMessage = firstContact.errorMessage,
            isError = firstContact.hasError,
            keyboardType = KeyboardType.Number
        )
        DropDownInput(
            value = firstRelationship.value,
            placeholder = "Please Select",
            onValueChange = { firstRelationship.change(it) },
            errorMessage = firstRelationship.errorMessage,
            isError = firstRelationship.hasError,
            options = relationshipOptions
        )

        InputLabel(label = "Second Contact")
        AltInput(
            placeholder = "Enter name",
            value = secondName.value,
            onValueChange = { secondName.change(it) },
            errorMessage = secondName.errorMessage,
            isError = secondName.hasError
        )
        AltInput(
            placeholder = "Enter contact",
            value = secondContact.value,
            onValueChange = { secondContact.change(it) },
            errorMessage = secondContact.errorMessage,
            isError = secondContact.hasError,
            keyboardType = KeyboardType.Number
        )
        DropDownInput(
            value = secondRelationship.value,
            placeholder = "Please Select",
            onValueChange = { secondRelationship.change(it) },
            errorMessage = secondRelationship.errorMessage,
            isError = secondRelationship.hasError,
            options = relationshipOptions
        )

        ButtonSection(label = "Submit") {
            keyboardController?.hide()
            openScreen(Screens.LimitProcessingScreen.route)
        }
    }
}