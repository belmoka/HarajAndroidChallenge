package com.example.harajtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.Data.ParceData

class PostViewModel(private val postData: ParceData): ViewModel() {
    var post: LiveData<ParceData> = MutableLiveData<ParceData>().apply {
        value = postData
    }

    @JvmName("getPost1")
    fun getPost(): LiveData<ParceData> {
        return post
    }
}