package com.example.wikipediasummarizer.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.ui.MainActivity
import com.example.wikipediasummarizer.ui.WikiViewModel
import com.example.wikipediasummarizer.util.Constants.Companion.SEARCH_ARTICLE_DELAY
import com.example.wikipediasummarizer.util.Resource
import kotlinx.android.synthetic.main.fragment_search_wiki.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchWikiFragment : Fragment(R.layout.fragment_search_wiki) {

    val TAG = "SEARCHWIKIFRAGMENT"

    lateinit var viewModel: WikiViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        var job: Job? = null

        search_wiki_search_view.requestFocus()
        showKeyboard()

        search_wiki_search_view.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_ARTICLE_DELAY)
                editable?.let {
                    if (editable.toString().isNotBlank()) {
                        viewModel.searchArticles(editable.toString())
                    }
                }
            }
        }

        viewModel.searchArticles.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { wikiResponse ->
                        Log.d(TAG, wikiResponse.query.search[0].title)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun showKeyboard() {
        val inputMethodManager =context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    }

    private fun hideProgressBar() {
        search_fragment_progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        search_fragment_progress_bar.visibility = View.VISIBLE
    }

}