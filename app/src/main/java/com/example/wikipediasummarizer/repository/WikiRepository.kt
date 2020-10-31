package com.example.wikipediasummarizer.repository

import com.example.wikipediasummarizer.api.RetrofitInstance
import com.example.wikipediasummarizer.api.RetrofitSummaryInstance

class WikiRepository {

    suspend fun searchArticles(search: String, searchArticlesOffset: Int) =
            RetrofitInstance.api.searchForArticles(gpssearch = search, gpsoffset = searchArticlesOffset)

    suspend fun getArticleSummary(url: String, sentences : Int) =
            RetrofitSummaryInstance.api.getSummary(url = url, sentences = sentences)

}