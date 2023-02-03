package com.loki.okoaloan.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DropDownInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    isError: Boolean,
    options: List<String>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedValue by remember {
        mutableStateOf(value)
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown


    Column(
        modifier = modifier
    ) {

        Box {
            TextField(
                value = selectedValue,
                onValueChange = {
                    selectedValue = it
                    onValueChange(it)
                },
                isError = isError,
                placeholder = {
                    Text(text = placeholder)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFieldSize = it.size.toSize()
                    },
                colors = textFieldColors(),
                keyboardOptions = KeyboardOptions.Default,
                trailingIcon = {
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(imageVector = icon, contentDescription = null)
                    }
                }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )
        }

        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                fontSize = 12.sp
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(
                with(LocalDensity.current) {
                    textFieldSize.width.toDp()
                }
            )
        ) {
            options.forEach {
                DropdownMenuItem(
                    onClick = {
                        keyboardController?.hide()
                        selectedValue = it
                        isExpanded = false
                    }
                ) {

                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 12.dp
                            ),
                    )
                }
            }
        }
    }

}