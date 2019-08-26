package com.efhems.newsonline

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.efhems.newsonline.adapter.AllNewsRecyclerAdapter
import com.efhems.newsonline.data.AllNews
import com.efhems.newsonline.viewmodel.AppViewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        appViewModel.fetchBitcoin("bitcoin", "2019-07-26", "publishedAt")
        appViewModel.fetchAppleNews("apple", "2019-08-25", "2019-08-25", "popularity")
        appViewModel.fetchBusiness("us", "business")


        appViewModel.bitconLiveData.observe(this, Observer { bitcoin ->
            val newsList = arrayListOf<AllNews>()

            for (i in 1..5) {
                val news = AllNews(bitcoin)
                newsList.add(news)
            }
            setUpRecyclerView(newsList)
        })


    }

    private fun setUpRecyclerView(news: List<AllNews>) {
        val newsRec = findViewById<RecyclerView>(R.id.recAllNews)
        newsRec.setHasFixedSize(true)
        val adapter = AllNewsRecyclerAdapter(this)
        adapter.submitList(news)
        newsRec.adapter = adapter

    }

    private fun getData(): List<AllNews> {
        var news: AllNews
        val newsList = arrayListOf<AllNews>()

        appViewModel.bitconLiveData.observe(this, Observer { bitcoin ->
            news = AllNews(bitcoin)
            newsList.add(news)
//            setUpRecyclerView(ne)
        })

//        appViewModel.appleLiveData.observe(this, Observer { apple ->
//            news = AllNews(apple)
//            newsList.add(news)
//        })
//
//        appViewModel.businessLiveData.observe(this, Observer { business ->
//            news = AllNews(business)
//            newsList.add(news)
//        })

        return newsList
    }
}

