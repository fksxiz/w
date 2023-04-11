package com.example.project4

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ApiRepository {
    // json to request body with media type...
    private val dataToRequestBody: (String) -> RequestBody =
        { it.toRequestBody("application/json; charset=utf-8".toMediaType()) }

    private val client = OkHttpClient()

    private val url = HttpUrl.Builder()
        .scheme("http")
        .host("medic.madskill.ru")
        .addPathSegments("api/sendCode")
        .build()

    fun sendCode(data: String, callback: (Boolean) -> Unit) {
        val request = Request.Builder().post(dataToRequestBody("dracosci123@gmail.com")).addHeader("email", "dracosci123@gmail.com").url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Server", "${e.message}")
                callback(false)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("Server", response.message)
                    callback(false)
                    return
                }
                callback(true)

            }

        })
    }

    fun AuthProfile(data: String, callback: () -> String) {
//        val request = Request.Builder().post(
//            dataToRequestBody(data)
//        ).url(
//
//        ).build()
    }

    fun CreateProfile(data: String, callback: () -> Unit) {
//        val request = Request.Builder().post(
//            dataToRequestBody(data)
//        ).url(
//
//        ).build()
    }
}