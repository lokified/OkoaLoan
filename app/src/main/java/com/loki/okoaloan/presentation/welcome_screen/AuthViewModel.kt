package com.loki.okoaloan.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.okoaloan.domain.model.User
import com.loki.okoaloan.domain.model.UserException
import com.loki.okoaloan.domain.use_cases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(authEvent: AuthEvent, phoneNumber: String, password: String) {

        when(authEvent) {

            AuthEvent.LoginUser -> {

                viewModelScope.launch {

                    try {
                        userUseCase.loginUser(
                            phoneNumber,
                            password
                        )
                    }
                    catch (e: UserException) {
                        if(e.message  == "login success") {

                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    message = "login success"
                                )
                            )

                            delay(3000L)
                            _eventFlow.emit(
                                UiEvent.LoginUser,

                            )


                        }
                        else {
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    message = e.message ?: "login failed"
                                )
                            )
                        }
                    }
                }
            }

            AuthEvent.RegisterUser -> {

                viewModelScope.launch {

                    userUseCase.registerUser(
                        User(
                            phoneNumber = phoneNumber,
                            password = password
                        )
                    )

                    _eventFlow.emit(
                        UiEvent.SaveUser
                    )

                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            message = "Registration successful"
                        )
                    )
                }
            }
        }
    }


    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveUser: UiEvent()
        object LoginUser: UiEvent()
    }

    sealed class AuthEvent{
        object LoginUser: AuthEvent()
        object RegisterUser: AuthEvent()
    }
}