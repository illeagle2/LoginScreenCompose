package com.example.loginscreencompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.loginscreencompose.ui.theme.LoginScreenComposeTheme
import com.example.loginscreencompose.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route,
                    ) {
                        composable(
                            route = Screen.UserScreen.route,
                            arguments = listOf(
                                navArgument(Constants.PARAM_EMAIL){
                                    type = NavType.StringType
                            })
                        ){ navBack ->
                            navBack.arguments?.getString(Constants.PARAM_EMAIL)?.let {
                                UserScreen(email = it)
                            }
                        }
                        composable(
                            route = Screen.LoginScreen.route
                        ){
                            LoginScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreenComposeTheme {
    }
}

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object UserScreen: Screen("user_screen/{${Constants.PARAM_EMAIL}}")
}