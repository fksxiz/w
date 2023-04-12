package com.example.project4

data class ProfileData(
    val id:Long,
    val firstName:String,
    val lastName:String,
    val middleName:String,
    val bith: Long,
    val gender: Boolean,
    val image: String
):java.io.Serializable