package com.example.wikipediasummarizer.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.ui.MainActivity
import com.example.wikipediasummarizer.ui.WikiViewModel
import com.example.wikipediasummarizer.util.Constants.Companion.WIKI_URL
import com.example.wikipediasummarizer.util.Resource
import kotlinx.android.synthetic.main.fragment_wiki_article.*

class WikiArticleFragment : Fragment(R.layout.fragment_wiki_article) {


    lateinit var viewModel: WikiViewModel
    val args: WikiArticleFragmentArgs by navArgs()
    var thisissostupid = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(WIKI_URL + args.Title)
        }

        summarize_article_fab.setOnClickListener {
            viewModel.getArticleSummary(WIKI_URL + args.Title.replace(" ", "_"))
        }

        viewModel.summary.observe(viewLifecycleOwner, Observer { response ->
            Log.d("FRIG", "Observing...")
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    if (thisissostupid) {
                        response.data.let { summaryResponse ->
                            val summaryMessage = summaryResponse?.summary?.replace(". ", "\n\n")!!.replace("[...]", "")
                            val builder = AlertDialog.Builder(activity)
                                    .setTitle("Summary")
                                    .setMessage(summaryMessage)
                                    .setPositiveButton("Great!", null)
                            builder.create().show()
                            thisissostupid = false
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    thisissostupid = true
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

}