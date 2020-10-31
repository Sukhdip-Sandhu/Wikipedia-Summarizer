package com.example.wikipediasummarizer.api

import com.example.wikipediasummarizer.util.Constants.Companion.SUMMARY_API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSummaryInstance {
    companion object {
        private val retrofit2 by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            Retrofit.Builder()
                    .baseUrl(SUMMARY_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }

        val api by lazy {
            retrofit2.create(SummaryAPI::class.java)
        }

    }
}