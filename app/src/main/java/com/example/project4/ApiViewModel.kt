package com.example.project4

import android.util.Log
import androidx.lifecycle.ViewModel

class ApiViewModel : ViewModel() {
    private val repository: ApiRepository = ApiRepository()
    fun SendCode(data: String) {
        repository.sendCode(data) {
            Log.d("Server", if (it) "Успех" else "Не успех")
        }
    }

    fun AuthProfile(data: EmailData){
        repository.AuthProfile(data){
            Log.d("Server", if (it) "Успех" else "Не успех")
            return@AuthProfile ""
        }
    }
}