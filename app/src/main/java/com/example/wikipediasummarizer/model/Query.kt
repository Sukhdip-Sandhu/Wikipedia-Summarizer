package com.example.wikipediasummarizer.model


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("search")
    val search: List<Search>,
    @SerializedName("searchinfo")
    val searchinfo: Searchinfo
)