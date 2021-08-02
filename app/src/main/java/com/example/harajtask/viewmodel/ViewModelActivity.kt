package com.example.harajtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.Api.Repository
import com.example.harajtask.Data.Data

class ViewModelActivity(private val data: Repository): ViewModel() {
    val ads: LiveData<List<Data>> = MutableLiveData<List<Data>>().apply {
        value = data.setData()
    }

    fun getData(): LiveData<List<Data>>{
        return ads
    }

    public fun fetchData() {
//        ads.value = data.setData()
    }
}