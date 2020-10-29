package com.example.wikipediasummarizer.repository

import com.example.wikipediasummarizer.api.RetrofitInstance
import retrofit2.Retrofit

class WikiRepository {

    suspend fun searchArticle(search: String) =
        RetrofitInstance.api.searchForArticles(srsearch = search)

}