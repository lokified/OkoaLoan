package com.loki.okoaloan.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Input(
    placeholder: String,
    label: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text
) {


    var passwordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        isError = isError,
        label = {
            Text(text = label, modifier = Modifier.padding(bottom = 8.dp))
        },
        placeholder = {
            Text(text = placeholder)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            focusedIndicatorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (!passwordVisible && keyboardType == KeyboardType.Password) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {

            if(keyboardType == KeyboardType.Password) {

                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        }
    )

    if (isError) {
        Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
    }

}


@Composable
fun InputLabel(
    modifier: Modifier = Modifier,
    labelNumber: String? = null,
    label: String,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "*",
            fontSize = 10.sp,
            color = Color.Red,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = labelNumber ?: "",
            fontSize = 12.sp
        )
        Text(
            text = label,
            fontSize = 12.sp
        )
    }
}


@Composable
fun AltInput(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        isError = isError,
        placeholder = {
            Text(text = placeholder)
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            focusedIndicatorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )

    if (isError) {
        Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
    }
}