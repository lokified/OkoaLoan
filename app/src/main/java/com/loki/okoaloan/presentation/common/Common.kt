package com.loki.okoaloan.presentation.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
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
    val bottomSheetState = rememberModalBottomSheetState()
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


    ModalBottomSheet(
        onDismissRequest = {

        },
        sheetState = bottomSheetState,
        content = {

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
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onBackground
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
