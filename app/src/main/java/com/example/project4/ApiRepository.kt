package com.example.project4

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ApiRepository {
    // json to request body with media type...
    private val dataToRequestBody: (String) -> RequestBody =
        { it.toRequestBody("application/json; charset=utf-8".toMediaType()) }

    private val client = OkHttpClient()
    private val accept = "application/json"

    fun sendCode(data: String, callback: (Boolean) -> Unit) {

        val url = HttpUrl.Builder()
            .scheme("https")
            .host("medic.madskill.ru")
            .addPathSegments("api/sendCode")
            .build()

        val request = Request.Builder().post(dataToRequestBody("")).addHeader("email", data)
            .addHeader("accept", accept).url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Server", "qwe${e.message}")
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

    fun AuthProfile(data: EmailData, callback: (String) -> Unit) {

        val email = data.email
        val code = data.code

        val url = HttpUrl.Builder()
            .scheme("https")
            .host("medic.madskill.ru")
            .addPathSegments("api/signin")
            .build()

        val request = Request.Builder().post(dataToRequestBody(""))
            .addHeader("accept", accept)
            .addHeader("email", email)
            .addHeader("code", code)
            .url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Server", "${e.message}")
                callback(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("Server", response.message)
                    callback(response.message)
                    return
                }

                val responseBody = response.body?.string() as String
                callback(parseAuthResponse(responseBody))
            }

        })
    }

    fun CreateProfile(data: String, callback: (String) -> Unit) {

        val request = Request.Builder().post(
            dataToRequestBody(data)
        ).url(
            HttpUrl.Builder()
                .scheme("https")
                .host("medic.madskill.ru")
                .addPathSegments("api/createProfile")
                .build()
        )
            .addHeader("Authorization", "Bearer ${BusinessToken.token}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Server", "${e.message}")
                callback(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("Server", response.message)
                    callback(response.message)
                    return
                }

                callback(response.message)

            }

        })
    }

    private fun parseAuthResponse(body: String): String {
        return try {

            val response = JSONObject(body)
            BusinessToken.token = response.getString("token")
            response.getString("token")

        } catch (e: JSONException) {
            Log.e("Parser", "parse response error = ${e.message}")
            "Parse error"
        }
    }

    fun UpdateProfile(data: String, callback: (String) -> Unit){
        val request = Request.Builder()
            .put(dataToRequestBody(data)).url(
                HttpUrl.Builder()
                    .scheme("https")
                    .host("medic.madskill.ru")
                    .addPathSegments("api/updateProfile")
                    .build()
            )
            .addHeader("Authorization", "Bearer ${BusinessToken.token}")
            .build()
        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("SERVER","${e.message}")
                callback("${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if(!response.isSuccessful){
                    Log.e("SERVER", response.message)
                    callback(response.message)
                }

                callback(response.message)
            }
        })
    }
}