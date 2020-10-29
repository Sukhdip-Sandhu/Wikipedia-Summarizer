package com.example.wikipediasummarizer.model


import com.google.gson.annotations.SerializedName

data class Searchinfo(
    @SerializedName("suggestion")
    val suggestion: String,
    @SerializedName("suggestionsnippet")
    val suggestionsnippet: String,
    @SerializedName("totalhits")
    val totalhits: Int
)