package com.example.wikipediasummarizer.models


import com.google.gson.annotations.SerializedName

data class WikiSearchResponse(
    @SerializedName("batchcomplete")
    val batchcomplete: Boolean,
    @SerializedName("continue")
    val continueX: Continue,
    @SerializedName("query")
    val query: Query
)