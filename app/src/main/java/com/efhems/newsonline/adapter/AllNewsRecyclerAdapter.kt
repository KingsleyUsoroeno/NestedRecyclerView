package com.efhems.newsonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhems.newsonline.R
import com.efhems.newsonline.data.AllNews
import com.efhems.newsonline.data.Article

class AllNewsRecyclerAdapter(private val ctx: Context) :
    ListAdapter<AllNews, AllNewsViewHolder>(AllNewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_all_news, parent, false)
        return AllNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        val allNews = getItem(position)
        holder.bindView(allNews.articles, ctx)
    }
}

class AllNewsDiffCallback : DiffUtil.ItemCallback<AllNews>() {

    override fun areItemsTheSame(oldItem: AllNews, newItem: AllNews): Boolean {
        return oldItem.articles == newItem.articles
    }

    override fun areContentsTheSame(oldItem: AllNews, newItem: AllNews): Boolean {
        return oldItem == newItem
    }
}


class AllNewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val bitCoinRec = view.findViewById<RecyclerView>(R.id.bitcoin_recyclerView)
    //private val businessRec = view.findViewById<RecyclerView>(R.id.business_recyclerView)

    fun bindView(article: List<Article>, ctx: Context) {
        val newsAdapter = NewsRecyclerAdapter(ctx)
        newsAdapter.submitList(article)
        // first RecyclerView
        bitCoinRec.setHasFixedSize(true)
        bitCoinRec.adapter = newsAdapter
        // sec RecyclerView
//        businessRec.setHasFixedSize(true)
//        businessRec.adapter = newsAdapter
    }

}
