package com.example.wikipediasummarizer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.repository.WikiRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: WikiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wikiRepository = WikiRepository()
        val viewModelProviderFactory = WikiViewModelProviderFactory(wikiRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(WikiViewModel::class.java)

    }
}