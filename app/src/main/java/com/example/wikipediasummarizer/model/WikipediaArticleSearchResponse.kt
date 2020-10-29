package com.example.wikipediasummarizer.model


import com.google.gson.annotations.SerializedName

data class WikipediaArticleSearchResponse(
    @SerializedName("batchcomplete")
    val batchcomplete: String,
    @SerializedName("continue")
    val continueX: Continue,
    @SerializedName("query")
    val query: Query
)