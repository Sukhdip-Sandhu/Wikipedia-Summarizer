package com.example.wikipediasummarizer.models.summary


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("code")
    val code: String,
    @SerializedName("credits")
    val credits: String,
    @SerializedName("msg")
    val msg: String
)