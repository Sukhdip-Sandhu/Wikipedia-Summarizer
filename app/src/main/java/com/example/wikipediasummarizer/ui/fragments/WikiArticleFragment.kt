package com.example.wikipediasummarizer.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.ui.MainActivity
import com.example.wikipediasummarizer.ui.WikiViewModel
import com.example.wikipediasummarizer.util.Constants.Companion.WIKI_BASE_URL
import kotlinx.android.synthetic.main.fragment_wiki_article.*
import okhttp3.OkHttpClient

class WikiArticleFragment : Fragment(R.layout.fragment_wiki_article) {


    lateinit var viewModel: WikiViewModel
    val args: WikiArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(WIKI_BASE_URL + args.Title)
        }
    }

}