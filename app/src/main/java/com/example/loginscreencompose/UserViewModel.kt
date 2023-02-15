package com.example.loginscreencompose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.loginscreencompose.utils.Constants

class UserViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var email: String = ""
    val password: String = ""

    init {
        savedStateHandle.get<String>(Constants.PARAM_EMAIL)?.let {name->
            getUser(name)
        }
    }

    private fun getUser(name: String) {
        email = name

    }


}