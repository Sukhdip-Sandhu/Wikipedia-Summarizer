package com.example.wikipediasummarizer.models.summary


import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("status")
    val status: Status,
    @SerializedName("summary")
    val summary: String
)