package com.example.finalproject.ui.appointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmationViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is confirmation fragment"
    }
    val text: LiveData<String> = _text
}