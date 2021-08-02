package com.example.harajtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harajtask.Api.Api
import com.example.harajtask.Api.Repository

class ViewModelFactory(private var api: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ViewModelActivity(api) as T
    }
}