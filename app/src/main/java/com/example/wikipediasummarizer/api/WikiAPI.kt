package com.example.wikipediasummarizer.api

import com.example.wikipediasummarizer.models.WikiSearchResponse
import com.example.wikipediasummarizer.util.Constants.Companion.QUERY_PAGE_SIZE
import com.example.wikipediasummarizer.util.Constants.Companion.THUMBNAIL_WIDTH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiAPI {

    @GET("w/api.php")
    suspend fun searchForArticles(
            @Query("action") action: String = "query",
            @Query("generator") generator: String = "prefixsearch",
            @Query("gpssearch") gpssearch: String = "",
            @Query("gpslimit") gpslimit: Int = QUERY_PAGE_SIZE,
            @Query("gpsoffset") gpsoffset: Int = 0,
            @Query("prop") prop: String = "pageimages|pageterms",
            @Query("piprop") piprop: String = "thumbnail",
            @Query("pithumbsize") pithumbsize: Int = THUMBNAIL_WIDTH,
            @Query("pilimit") pilimit: Int = 20,
            @Query("wbptterms") wbptterms: String = "description",
            @Query("formatversion") formatversion: Int = 2,
            @Query("format") format: String = "json"
    ): Response<WikiSearchResponse>


}