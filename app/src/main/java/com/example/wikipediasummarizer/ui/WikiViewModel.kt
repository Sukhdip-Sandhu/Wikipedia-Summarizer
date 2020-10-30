package com.example.wikipediasummarizer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikipediasummarizer.models.WikiSearchResponse
import com.example.wikipediasummarizer.repository.WikiRepository
import com.example.wikipediasummarizer.util.Constants.Companion.QUERY_PAGE_SIZE
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
    var isScrolling = false

    fun searchArticles(search: String, isScroll: Boolean) = viewModelScope.launch {
        isScrolling = isScroll
        if (isScrolling) searchArticlesOffset += QUERY_PAGE_SIZE else searchArticlesOffset = 0
        searchArticles.postValue(Resource.Loading())
        val response = wikiRespository.searchArticles(search, searchArticlesOffset)
        searchArticles.postValue(handleSearchArticlesResponse(response))
    }

    private fun handleSearchArticlesResponse(response: Response<WikiSearchResponse>): Resource<WikiSearchResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (resultResponse.query!=null) {
                    if (searchArticleResponse == null || !isScrolling) {
                        searchArticleResponse = resultResponse
                    } else {
                        if (isScrolling) {
                            val oldArticles = searchArticleResponse?.query?.pages
                            val newArticles = resultResponse.query.pages
                            oldArticles?.addAll(newArticles)
                        } else {
                            return Resource.Success(resultResponse)
                        }
                    }
                    return Resource.Success(searchArticleResponse ?: resultResponse)
                }
            }
        }
        return Resource.Error("Can't retrieve any further pages")
    }

}