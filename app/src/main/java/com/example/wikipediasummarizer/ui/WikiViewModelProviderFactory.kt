package com.example.wikipediasummarizer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wikipediasummarizer.repository.WikiRepository

class WikiViewModelProviderFactory(
    val wikiRepository: WikiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WikiViewModel(wikiRepository) as T
    }
}