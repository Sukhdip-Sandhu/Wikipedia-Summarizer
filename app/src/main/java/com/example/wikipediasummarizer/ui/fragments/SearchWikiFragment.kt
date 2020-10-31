package com.example.wikipediasummarizer.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.adapters.WikiArticleAdapter
import com.example.wikipediasummarizer.ui.MainActivity
import com.example.wikipediasummarizer.ui.WikiViewModel
import com.example.wikipediasummarizer.util.Constants
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
    lateinit var wikiArticleAdapter: WikiArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        search_wiki_search_view.requestFocus()
        showKeyboard()
        setupRecyclerView()

        wikiArticleAdapter.setOnItemClickListener { wikiArticle ->
            val bundle = Bundle().apply {
                putString("Title", wikiArticle.title)
            }
            findNavController().navigate(
                R.id.action_searchWikiFragment_to_wikiArticleFragment,
                bundle
            )
        }

        var job: Job? = null

        search_wiki_search_view.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_ARTICLE_DELAY)
                editable?.let {
                    if (editable.toString().isNotBlank()) {
                        viewModel.searchArticles(editable.toString(), false)
                    } else {
                        wikiArticleAdapter.differ.submitList(emptyList())
                    }
                }
            }
        }

        viewModel.searchArticles.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { wikiResponse ->
                        wikiArticleAdapter.differ.submitList(wikiResponse.query.pages.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun showKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

    }

    private fun hideProgressBar() {
        search_fragment_progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        search_fragment_progress_bar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        wikiArticleAdapter = WikiArticleAdapter()
        wiki_article_recyclerview.apply {
            adapter = wikiArticleAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@SearchWikiFragment.scrollListener)
        }

    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.searchArticles(search_wiki_search_view.text.toString(), true)
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

    }

}