package com.example.project4

import android.util.Log
import androidx.lifecycle.ViewModel

class ApiViewModel : ViewModel() {
    private val repository: ApiRepository = ApiRepository()
    var token = ""

    fun SendCode(data: String) {
        repository.sendCode(data) {
            Log.d("Server", if (it) "Успех" else "Не успех")
        }
    }

    fun AuthProfile(data: EmailData) {
        repository.AuthProfile(data) {
            Log.d("Server", it)
            token = it
        }
    }

    fun CreateProfile(
        id: Int,
        firstName: String,
        lastName: String,
        middleName: String,
        bith: String,
        gender: String,
        image: String
    ) {
        val data = "{" +
                "\"id\": ${id}," +
                "\"firstname\": \"${firstName}\"," +
                "\"lastname\": \"${lastName}\"," +
                "\"middlename\": \"${middleName}\"," +
                "\"bith\": \"${bith}\"," +
                "\"pol\": \"${gender}\"," +
                "\"image\": \"${image}\"" +
                "}"
        repository.CreateProfile(data) {
            Log.d("Server", it)
        }
    }
}