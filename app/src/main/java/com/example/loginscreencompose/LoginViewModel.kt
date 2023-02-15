package com.example.loginscreencompose


import androidx.lifecycle.ViewModel


class LoginViewModel() : ViewModel() {


    fun login(userName: String, password: String): Boolean {
        //code to call repo and check if username/password is valid
        return true

    }
}