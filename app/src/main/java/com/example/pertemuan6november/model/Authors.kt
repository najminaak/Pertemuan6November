package com.example.pertemuan6november.model

import com.google.gson.annotations.SerializedName

data class Authors(
    @SerializedName("id")
    val id : Int,
    @SerializedName("email")
    val email : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("avatar")  //harus sama yang di return gson
    val avatar : String,  //fleksibel
)
