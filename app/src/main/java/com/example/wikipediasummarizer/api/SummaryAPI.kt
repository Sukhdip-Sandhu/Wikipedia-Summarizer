package com.example.wikipediasummarizer.api

import com.example.wikipediasummarizer.models.summary.SummaryResponse
import com.example.wikipediasummarizer.util.Constants.Companion.SUMMARY_API_KEY
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface SummaryAPI {

    @POST("summarization-1.0")
    suspend fun getSummary(
            @Query("key")
            apiKey: String = SUMMARY_API_KEY,
            @Query("url")
            url: String = "https://en.wikipedia.org/wiki/ARTICLE",
            @Query("sentences")
            sentences: Int = 3
    ): Response<SummaryResponse>

}