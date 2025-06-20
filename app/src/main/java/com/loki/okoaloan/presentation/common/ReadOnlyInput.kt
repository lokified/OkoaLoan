package com.loki.okoaloan.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun ReadOnlyInput(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    isError: Boolean,
    errorMessage: String,
    onClick: () -> Unit
) {


    Box(modifier = modifier) {

        TextField(
            value = value,
            onValueChange = {},
            isError = isError,
            placeholder = {
                Text(text = placeholder)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable { onClick() }
        )

        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }
    }
}
