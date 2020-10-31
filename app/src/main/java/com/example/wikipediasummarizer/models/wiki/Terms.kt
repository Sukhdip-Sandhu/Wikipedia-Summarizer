package com.example.wikipediasummarizer.models.wiki


import com.google.gson.annotations.SerializedName

data class Terms(
    @SerializedName("description")
    val description: List<String>
)