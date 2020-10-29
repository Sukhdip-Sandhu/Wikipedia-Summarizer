package com.example.wikipediasummarizer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikipediasummarizer.model.WikipediaArticleSearchResponse
import com.example.wikipediasummarizer.repository.WikiRepository
import com.example.wikipediasummarizer.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WikiViewModel(
    val wikiRespository: WikiRepository
) : ViewModel() {

    val searchArticles: MutableLiveData<Resource<WikipediaArticleSearchResponse>> =
        MutableLiveData()

    suspend fun searchArticles(search: String) = viewModelScope.launch {
        searchArticles.postValue(Resource.Loading())
        val response = wikiRespository.searchArticle(search)
        searchArticles.postValue(handleSearchArticlesResponse(response))
    }

    private fun handleSearchArticlesResponse(response: Response<WikipediaArticleSearchResponse>): Resource<WikipediaArticleSearchResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}