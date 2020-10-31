package com.example.wikipediasummarizer.models.wiki


import com.google.gson.annotations.SerializedName

data class Page(
        @SerializedName("index")
    val index: Int,
        @SerializedName("ns")
    val ns: Int,
        @SerializedName("pageid")
    val pageid: Int,
        @SerializedName("terms")
    val terms: Terms,
        @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
        @SerializedName("title")
    val title: String
)