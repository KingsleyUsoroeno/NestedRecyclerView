package com.efhems.newsonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.efhems.newsonline.R
import com.efhems.newsonline.data.Article

class NewsRecyclerAdapter(private val ctx: Context) : ListAdapter<Article, NewsViewHolder>(NewsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bindViews(ctx, article)
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imgArticle = view.findViewById<ImageView>(R.id.img_article)
    private val tvAuthor = view.findViewById<TextView>(R.id.tv_author)

    fun bindViews(ctx: Context, article: Article) {
        Glide.with(ctx).load(article.urlToImage)
            .apply(RequestOptions().placeholder(R.drawable.ic_loading_animation))
            .error(R.drawable.ic_broken_image)
            .into(imgArticle)
        tvAuthor.text = article.author
    }

}
