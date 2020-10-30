package com.example.wikipediasummarizer.models


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("search")
    val search: MutableList<Search>,
    @SerializedName("searchinfo")
    val searchinfo: Searchinfo
)