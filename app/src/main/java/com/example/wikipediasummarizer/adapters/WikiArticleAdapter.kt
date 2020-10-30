package com.example.wikipediasummarizer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.models.Search
import kotlinx.android.synthetic.main.item_wiki_article_preview.view.*

class WikiArticleAdapter : RecyclerView.Adapter<WikiArticleAdapter.WikiArticleViewHolder>() {

    inner class WikiArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.pageid == newItem.pageid
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WikiArticleViewHolder {
        return WikiArticleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_wiki_article_preview,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: WikiArticleViewHolder, position: Int) {
        val wikiArticle = differ.currentList[position]
        holder.itemView.apply {
            wiki_article_preview_title.text = wikiArticle.title
            wiki_article_preview_snippit.text = wikiArticle.format_snippet()
            setOnClickListener {
                onItemClickListener?.let { it(wikiArticle) }
            }
        }

    }

    private var onItemClickListener: ((Search) -> Unit)? = null

    fun setOnItemClickListener(listener: (Search) -> Unit) {
        onItemClickListener = listener
    }

}