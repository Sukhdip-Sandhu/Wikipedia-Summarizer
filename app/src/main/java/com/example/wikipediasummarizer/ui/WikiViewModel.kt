package com.example.wikipediasummarizer.ui

import androidx.lifecycle.ViewModel
import com.example.wikipediasummarizer.repository.WikiRepository

class WikiViewModel(
    val wikiRespository: WikiRepository
) : ViewModel() {
}