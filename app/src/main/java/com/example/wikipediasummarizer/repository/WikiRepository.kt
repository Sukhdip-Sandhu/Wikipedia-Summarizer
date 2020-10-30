package com.example.wikipediasummarizer.repository

import com.example.wikipediasummarizer.api.RetrofitInstance

class WikiRepository {

    suspend fun searchArticle(search: String, searchArticlesOffset: Int) =
        RetrofitInstance.api.searchForArticles(srsearch = search, sroffset = searchArticlesOffset)

}