package com.example.wikipediasummarizer.models.wiki


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("pages")
    val pages: MutableList<Page>
)