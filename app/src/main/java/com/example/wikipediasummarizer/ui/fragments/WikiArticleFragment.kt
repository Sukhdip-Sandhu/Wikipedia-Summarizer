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
import java.text.BreakIterator
import java.util.*

class WikiArticleFragment : Fragment(R.layout.fragment_wiki_article) {


    lateinit var viewModel: WikiViewModel
    private val args: WikiArticleFragmentArgs by navArgs()
    var showSummaryAlertDialog = false

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
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    if (showSummaryAlertDialog) {
                        response.data.let { summaryResponse ->
                            val summaryMessage = summaryResponse?.summary?.replace(". ", "\n\n")!!.replace("[...]", "")
                            val builder = AlertDialog.Builder(activity)
                                    .setTitle("AI Summary")
                                    .setIcon(R.drawable.ic_baseline_assistant_24_blue)
                                    .setMessage(summaryMessage)
                                    .setPositiveButton("Great!", null)
                            builder.create().show()
                            showSummaryAlertDialog = false
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showSummaryAlertDialog = true
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