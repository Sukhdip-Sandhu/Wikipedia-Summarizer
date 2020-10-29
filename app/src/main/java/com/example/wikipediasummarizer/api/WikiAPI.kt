package com.example.wikipediasummarizer.api

import com.example.wikipediasummarizer.model.WikipediaArticleSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiAPI {

    @GET("w/api.php")
    suspend fun searchForArticles(
        @Query("action") action: String = "query",
        @Query("list") list: String = "search",
        @Query("srsearch") srsearch: String = "anime",
        @Query("srlimit") srlimit: Int = 10,
        @Query("prop") prop: String = "info",
        @Query("inprop") inprop: String = "url",
        @Query("format") format: String = "json"
    ): Response<WikipediaArticleSearchResponse>

}