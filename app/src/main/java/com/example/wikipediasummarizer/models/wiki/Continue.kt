package com.example.wikipediasummarizer.models.wiki


import com.google.gson.annotations.SerializedName

data class Continue(
    @SerializedName("continue")
    val continueX: String,
    @SerializedName("gpsoffset")
    val gpsoffset: Int
)