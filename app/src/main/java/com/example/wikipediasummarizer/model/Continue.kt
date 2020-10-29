package com.example.wikipediasummarizer.model


import com.google.gson.annotations.SerializedName

data class Continue(
    @SerializedName("continue")
    val continueX: String,
    @SerializedName("sroffset")
    val sroffset: Int
)