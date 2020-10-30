package com.example.wikipediasummarizer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikipediasummarizer.R
import com.example.wikipediasummarizer.models.Page
import kotlinx.android.synthetic.main.item_wiki_article_preview.view.*

class WikiArticleAdapter : RecyclerView.Adapter<WikiArticleAdapter.WikiArticleViewHolder>() {

    inner class WikiArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Page>() {
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem.pageid == newItem.pageid
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
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
            wikiArticle.thumbnail?.let {
                Glide.with(this).load(wikiArticle.thumbnail.source).into(wiki_article_preview_image)
            }
            wiki_article_preview_title.text = wikiArticle.title
            wikiArticle.terms?.let {
                wiki_article_preview_snippit.text = wikiArticle.terms.description.firstOrNull()
            }
            setOnClickListener {
                onItemClickListener?.let { it(wikiArticle) }
            }
        }

    }

    private var onItemClickListener: ((Page) -> Unit)? = null

    fun setOnItemClickListener(listener: (Page) -> Unit) {
        onItemClickListener = listener
    }

}