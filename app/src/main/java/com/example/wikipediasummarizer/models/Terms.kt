package com.example.wikipediasummarizer.models


import com.google.gson.annotations.SerializedName

data class Terms(
    @SerializedName("description")
    val description: List<String>
)