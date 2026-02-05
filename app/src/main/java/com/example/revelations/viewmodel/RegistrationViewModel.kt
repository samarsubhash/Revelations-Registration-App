package com.example.revelations.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationViewModel : ViewModel() {

    val name = MutableStateFlow("")
    val mobile = MutableStateFlow("")
    val email = MutableStateFlow("")
    val college = MutableStateFlow("")
    val group = MutableStateFlow("")
    val events = MutableStateFlow<List<String>>(emptyList())

}
