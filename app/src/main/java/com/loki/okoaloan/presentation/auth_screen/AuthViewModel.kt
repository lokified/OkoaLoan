package com.loki.okoaloan.presentation.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.use_cases.UserUseCase
import com.loki.okoaloan.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {


    val loginFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Phone",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    val registerFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Phone",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Password",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Confirm Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    private val _authEvent = MutableSharedFlow<AuthEvent>()
    val authEvent = _authEvent.asSharedFlow()

    fun loginUser(phoneNumber: String, password: String) {

        userUseCase.loginUser(phoneNumber, password).onEach { result ->

            when(result){

                is Resource.Loading -> {
                    _authEvent.emit(AuthEvent.Loading)
                }

                is Resource.Success -> {
                    _authEvent.emit(AuthEvent.LoginSuccess(result.message ?: ""))
                }

                is Resource.Error -> {
                    _authEvent.emit(AuthEvent.Error(result.message ?: "Something went wrong"))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun registerUser(phoneNumber: String, password: String) {
        userUseCase.registerUser(
            User(
                phoneNumber = phoneNumber, password = password
            )

        ).onEach { result ->

            when(result){

                is Resource.Loading -> {
                    _authEvent.emit(AuthEvent.Loading)
                }

                is Resource.Success -> {
                    _authEvent.emit(AuthEvent.RegisterSuccess(result.data ?: ""))
                }

                is Resource.Error -> {
                    _authEvent.emit(AuthEvent.Error(result.message ?: "Something went wrong"))
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class AuthEvent {
        object Loading: AuthEvent()
        data class LoginSuccess(val message: String): AuthEvent()
        data class RegisterSuccess(val message: String): AuthEvent()
        data class Error(val error: String): AuthEvent()
    }
}