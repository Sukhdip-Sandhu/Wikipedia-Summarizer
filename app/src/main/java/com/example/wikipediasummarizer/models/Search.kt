package com.example.wikipediasummarizer.models


import com.google.gson.annotations.SerializedName

data class Search(
        @SerializedName("ns")
        val ns: Int,
        @SerializedName("pageid")
        val pageid: Int,
        @SerializedName("size")
        val size: Int,
        @SerializedName("snippet")
        val snippet: String,
        @SerializedName("timestamp")
        val timestamp: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("wordcount")
        val wordcount: Int
) {
    fun format_snippet(): String {
        return snippet.replace("<span class=\"searchmatch\">", "").replace("</span>", "")
    }
}