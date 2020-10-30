package com.example.wikipediasummarizer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikipediasummarizer.models.WikiSearchResponse
import com.example.wikipediasummarizer.repository.WikiRepository
import com.example.wikipediasummarizer.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WikiViewModel(
        val wikiRespository: WikiRepository
) : ViewModel() {

    val searchArticles: MutableLiveData<Resource<WikiSearchResponse>> =
            MutableLiveData()
    var searchArticlesOffset = 0
    var searchArticleResponse: WikiSearchResponse? = null

    fun searchArticles(search: String) = viewModelScope.launch {
        searchArticles.postValue(Resource.Loading())
        val response = wikiRespository.searchArticles(search, searchArticlesOffset)
        searchArticles.postValue(handleSearchArticlesResponse(response))
    }

    private fun handleSearchArticlesResponse(response: Response<WikiSearchResponse>): Resource<WikiSearchResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchArticlesOffset = resultResponse.continueX.gpsoffset
                if (searchArticleResponse == null) {
                    searchArticleResponse = resultResponse
                } else {
                    val oldArticles = searchArticleResponse?.query?.pages
                    val newArticles = resultResponse.query.pages
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchArticleResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}