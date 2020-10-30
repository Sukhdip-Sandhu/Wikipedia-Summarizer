package com.example.wikipediasummarizer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikipediasummarizer.models.WikipediaArticleSearchResponse
import com.example.wikipediasummarizer.repository.WikiRepository
import com.example.wikipediasummarizer.util.Constants.Companion.QUERY_PAGE_SIZE
import com.example.wikipediasummarizer.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WikiViewModel(
        val wikiRespository: WikiRepository
) : ViewModel() {

    val searchArticles: MutableLiveData<Resource<WikipediaArticleSearchResponse>> =
            MutableLiveData()
    var searchArticlesOffset = 0
    var searchArticleResponse: WikipediaArticleSearchResponse? = null

    fun searchArticles(search: String) = viewModelScope.launch {
        searchArticles.postValue(Resource.Loading())
        val response = wikiRespository.searchArticle(search, searchArticlesOffset * QUERY_PAGE_SIZE)
        searchArticles.postValue(handleSearchArticlesResponse(response))
    }

    private fun handleSearchArticlesResponse(response: Response<WikipediaArticleSearchResponse>): Resource<WikipediaArticleSearchResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchArticlesOffset++
                if (searchArticleResponse == null) {
                    searchArticleResponse = resultResponse
                } else {
                    val oldArticles = searchArticleResponse?.query?.search
                    val newArticles = resultResponse.query.search
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchArticleResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}