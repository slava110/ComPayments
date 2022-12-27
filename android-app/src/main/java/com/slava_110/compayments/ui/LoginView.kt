package com.slava_110.compayments.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.slava_110.compayments.R

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginView() {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var state by rememberSaveable { mutableStateOf(LoginState.SIGNED_OUT) }

    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {

        LoginFields(
            login = login,
            password = password,
            onLoginChange = { login = it },
            onPasswordChange = { password = it }
        )
        when(state) {
            LoginState.SIGNED_OUT -> {

            }
            LoginState.SIGNING_IN -> {
                CircularProgressIndicator()
            }
            LoginState.SIGNED_IN -> {

                state = LoginState.SIGNED_OUT
            }
            LoginState.ERROR -> {

                state = LoginState.SIGNED_OUT
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    login: String,
    password: String,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = login,
        onValueChange = onLoginChange,
        label = { stringResource(R.string.auth_login) },
        placeholder = { Text(text = "example@gmail.com") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") }
    )
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { stringResource(R.string.auth_password) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        leadingIcon = { Icon(Icons.Default.Password, contentDescription = "Password") },
        visualTransformation = PasswordVisualTransformation()
    )
}

enum class LoginState {
    SIGNED_OUT,
    SIGNING_IN,
    SIGNED_IN,
    ERROR
}