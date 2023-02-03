package com.loki.okoaloan.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.okoaloan.util.SnackbarManager
import com.loki.okoaloan.util.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class OkoaLoanViewModel: ViewModel() {

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
            },
            block = block
        )
}