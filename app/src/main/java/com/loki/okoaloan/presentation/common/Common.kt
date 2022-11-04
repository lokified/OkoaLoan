package com.loki.okoaloan.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun Input(
    placeholder: String,
    label: String,
    value: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    var valueInput by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }


    TextField(
        value = valueInput,
        onValueChange = {
            valueInput = it
            value(it)
        },
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

}


@Composable
fun ButtonSection(
    label: String,
    onButtonClick:() -> Unit
) {

    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(100.dp))
    ) {

        Text(text = label)
    }
}


@Composable
fun TopBar(title: String) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 1.dp,

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}