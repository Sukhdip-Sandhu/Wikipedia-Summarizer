package com.example.wikipediasummarizer.util

class Constants {
    companion object {
        // Wikipedia
        const val WIKI_API_BASE_URL: String = "https://en.wikipedia.org"
        const val WIKI_URL: String = "https://en.wikipedia.org/wiki/"
        const val SEARCH_ARTICLE_DELAY = 500L
        const val QUERY_PAGE_SIZE = 15
        const val THUMBNAIL_WIDTH = 100

        // Summary
        const val SUMMARY_API_BASE_URL = "https://api.meaningcloud.com"
        const val SUMMARY_API_KEY = "3f363a515f1846b530f13d64f85d7eaa"
        const val SUMMARY_SENTENCES = 5
    }
}