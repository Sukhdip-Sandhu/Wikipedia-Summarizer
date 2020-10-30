package com.example.wikipediasummarizer.repository

import com.example.wikipediasummarizer.api.RetrofitInstance

class WikiRepository {

    suspend fun searchArticles(search: String, searchArticlesOffset: Int) =
            RetrofitInstance.api.searchForArticles(gpssearch = search, gpsoffset = searchArticlesOffset)

}