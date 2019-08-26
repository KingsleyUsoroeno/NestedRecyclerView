package com.efhems.newsonline.network

import com.efhems.newsonline.data.News
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //https://newsapi.org/v2/everything?q=bitcoin&from=2019-07-26&sortBy=publishedAt&apiKey=b37668ef0d1e4ac283ad4c621cc396cf
    @GET("v2/everything")
    fun fetchBitcoinAsync(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sorted: String,
        @Query("apiKey") apiKey: String

    ): Deferred<News>


    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=b37668ef0d1e4ac283ad4c621cc396cf
    @GET("v2/top-headlines")
    fun fetchBusiness(
        @Query("country") country: String,
        @Query("category") cat: String,
        @Query("apiKey") apiKey: String

    ): Deferred<News>

    //https://newsapi.org/v2/everything?q=apple&from=2019-08-25&to=2019-08-25&sortBy=popularity&apiKey=b37668ef0d1e4ac283ad4c621cc396cf
    @GET("v2/everything")
    fun fetchAppleNewsAsync(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sorted: String,
        @Query("apiKey") apiKey: String

    ): Deferred<News>


}