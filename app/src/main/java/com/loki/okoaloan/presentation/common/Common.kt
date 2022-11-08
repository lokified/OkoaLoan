package com.loki.okoaloan.presentation.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.launch

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
        mutableStateOf("")
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    
    
    Column {

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
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedLabelColor = Color.Black,
                    focusedIndicatorColor = Color.Black
                ),
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
            Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
        )
        
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable { onClick() }
        )

        if (isError) {
            Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetSelection(
    selectedValue: String,
    placeholder: String,
    isError: Boolean,
    errorMessage: String,
    options: List<String>,
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val (value, setValue) = remember {
        mutableStateOf(selectedValue)
    }
    
    BackHandler(bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    val toggleBottomSheetState = {
        coroutineScope.launch {

            if (bottomSheetState.isVisible) {
                bottomSheetState.hide()
            }
            else {
                bottomSheetState.show()
            }
        }
    }
    
    
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            
            options.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                setValue(it)
                                toggleBottomSheetState()
                            }
                            .padding(
                                horizontal = 16.dp,
                                vertical = 12.dp
                            ),
                    )
            }
        },
    ) {

        ReadOnlyInput(
            value = value,
            onClick = {
                toggleBottomSheetState()
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 4.dp
                ),
            placeholder = placeholder,
            isError = isError,
            errorMessage = errorMessage
        )
    }
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