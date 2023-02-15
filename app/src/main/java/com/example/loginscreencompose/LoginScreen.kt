package com.example.loginscreencompose

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlin.coroutines.coroutineContext


@Composable
fun LoginScreen(
//    viewModel: LoginViewModel,
    navController: NavController
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val viewModel = LoginViewModel()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        LoginFields(
            email,
            password,
            onLoginClick = {
                if(viewModel.login(email, password)){
                    navController.navigate("user_screen/$email")
                }else{
                    //navigate to error or new user screen
                    //navController.navigate()
                }
            },
            onEmailChange = { email = it},
            onPasswordChange = { password = it}
        )
    }
}

@Composable
fun LoginFields(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login")

        OutlinedTextField(
            value = email,
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "email") },
            onValueChange = onEmailChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        OutlinedTextField(
            value = password,
            placeholder = { Text(text = "password") },
            label = { Text(text = "password") },
            onValueChange = onPasswordChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()){
                    onLoginClick(email)
                    focusManager.clearFocus()
                } else {
                    Log.d("onLoginClicked", "missing email or password")
                }
            }) {
            Text(text = "Login")
        }
    }

}