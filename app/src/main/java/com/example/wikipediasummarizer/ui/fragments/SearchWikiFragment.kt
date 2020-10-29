package com.example.wikipediasummarizer.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.ui.MainActivity
import com.example.wikipediasummarizer.ui.WikiViewModel
import kotlinx.android.synthetic.main.fragment_search_wiki.*

class SearchWikiFragment : Fragment(R.layout.fragment_search_wiki) {

    lateinit var viewModel: WikiViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }


}