package com.efhems.newsonline.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efhems.newsonline.Constant
import com.efhems.newsonline.data.Article
import com.efhems.newsonline.network.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "AppViewModel"

class AppViewModel : ViewModel() {

    private val _bitcoinMutableLiveData = MutableLiveData<List<Article>>()
    private val _businessMutableLiveData = MutableLiveData<List<Article>>()
    private val _appleMutableLiveData = MutableLiveData<List<Article>>()

    private val job = Job()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)


    val bitconLiveData: LiveData<List<Article>>
        get() = _bitcoinMutableLiveData

    val businessLiveData: LiveData<List<Article>>
        get() = _businessMutableLiveData

    val appleLiveData: LiveData<List<Article>>
        get() = _appleMutableLiveData

    fun fetchBitcoin(query: String, from: String, sort: String) {
        viewModelScope.launch {
            val result = Network.service.fetchBitcoinAsync(query, from, sort, Constant.API_KEY)
            try {
                _bitcoinMutableLiveData.value = result.await().articles
                Log.i(TAG, "Fetched bitcoin data successfully ${result.await().articles.size}")

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun fetchBusiness(country: String, cat: String) {
        viewModelScope.launch {
            val result = Network.service.fetchBusiness(country, cat, Constant.API_KEY)
            try {
                _businessMutableLiveData.value = result.await().articles

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    fun fetchAppleNews(query: String, from: String, to: String, sort: String) {
        viewModelScope.launch {
            val result = Network.service.fetchAppleNewsAsync(query, from, to, sort, Constant.API_KEY)
            try {
                _appleMutableLiveData.value = result.await().articles

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}