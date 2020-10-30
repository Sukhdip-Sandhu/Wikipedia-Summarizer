package com.example.wikipediasummarizer.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wikipediasummarizer.R
import kotlinx.android.synthetic.main.fragment_wiki_article.*

class WikiArticleFragment : Fragment(R.layout.fragment_wiki_article) {


    val args: WikiArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wiki_article_title.text = args.Title
    }

}