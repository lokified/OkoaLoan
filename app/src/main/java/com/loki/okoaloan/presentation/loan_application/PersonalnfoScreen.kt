package com.loki.okoaloan.presentation.loan_application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dsc.form_builder.TextFieldState
import com.loki.okoaloan.presentation.navigation.Screens
import com.loki.okoaloan.presentation.common.*

@Composable
fun PersonalInfoScreen(
    openScreen: (String) -> Unit
) {

    Scaffold(topBar = { TopBar(title = "Personal Information") }) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface))

        PersonalInfoFormSection(
            openScreen = openScreen,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PersonalInfoFormSection(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel = LoanApplicationViewModel()
    val formState = remember{ viewModel.personalInfoState }
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    val educationOptions = listOf("Bachelors Degree", "Undergraduate", "Masters", "phd")
    val incomeOptions = listOf("10k - 20k", "20k - 50k", "50k - 70k", "70k - 100k", "100k - above")
    val maritalStatusOptions = listOf("Married", "Single", "Divorced")
    val workStatusOptions = listOf("Employed", "Self-employed", "Student", "unemployed")
    val loanPurposeOptions = listOf("Business", "Medicine", "Entertainment", "Others")
    val education = formState.getState<TextFieldState>("education")
    val income = formState.getState<TextFieldState>("incomeStatus")
    val maritalStatus = formState.getState<TextFieldState>("maritalStatus")
    val workStatus = formState.getState<TextFieldState>("workStatus")
    val loanPurpose = formState.getState<TextFieldState>("loanPurpose")


    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {


        InputLabel(labelNumber = "1.", label = "What is your education?")
        DropDownInput(
            value = education.value,
            placeholder = "Please Select",
            onValueChange = { education.change(it) },
            errorMessage = education.errorMessage,
            isError = education.hasError,
            options = educationOptions
        )

        InputLabel(labelNumber = "2.", label = "What is your income")
        DropDownInput(
            value = income.value,
            placeholder = "Please Select",
            onValueChange = { income.change(it) },
            errorMessage = income.errorMessage,
            isError = income.hasError,
            options = incomeOptions
        )

        InputLabel(labelNumber = "3.", label = "What is your marital status?")
        DropDownInput(
            value = maritalStatus.value,
            placeholder = "Please Select",
            onValueChange = { maritalStatus.change(it) },
            errorMessage = maritalStatus.errorMessage,
            isError = maritalStatus.hasError,
            options = maritalStatusOptions
        )

        InputLabel(labelNumber = "4.", label = "What is your work status?")
        DropDownInput(
            value = workStatus.value,
            placeholder = "Please Select",
            onValueChange = { workStatus.change(it) },
            errorMessage = workStatus.errorMessage,
            isError = workStatus.hasError,
            options = workStatusOptions
        )

        InputLabel(labelNumber = "5.", label = "What is your loan purpose?")
        DropDownInput(
            value = loanPurpose.value,
            placeholder = "Please Select",
            onValueChange = { loanPurpose.change(it) },
            errorMessage = loanPurpose.errorMessage,
            isError = loanPurpose.hasError,
            options = loanPurposeOptions
        )

        ButtonSection(label = "Continue") {

            keyboardController?.hide()
//            if (formState.validate()) {
//
//            }
            openScreen(Screens.ContactInfoScreen.route)
        }
    }
}