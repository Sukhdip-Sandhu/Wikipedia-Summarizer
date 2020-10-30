package com.example.wikipediasummarizer.api

import com.example.wikipediasummarizer.models.WikiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiAPI {

    @GET("w/api.php")
    suspend fun searchForArticles(
            @Query("action") action: String = "query",
            @Query("generator") generator: String = "prefixsearch",
            @Query("gpssearch") gpssearch: String = "anime",
            @Query("gpslimit") gpslimit: Int = 20,
            @Query("gpsoffset") gpsoffset: Int = 0,
            @Query("prop") prop: String = "pageimages|pageterms",
            @Query("piprop") piprop: String = "thumbnail",
            @Query("pithumbsize") pithumbsize: Int = 50,
            @Query("pilimit") pilimit: Int = 20,
            @Query("wbptterms") wbptterms: String = "description",
            @Query("formatversion") formatversion: Int = 2,
            @Query("format") format: String = "json"
    ): Response<WikiSearchResponse>


}