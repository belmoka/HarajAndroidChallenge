package com.example.harajtask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harajtask.Api.Api
import com.example.harajtask.Api.Repository
import com.example.harajtask.Data.Data
import com.example.harajtask.Adapter.DataAdapter
import com.example.harajtask.R
import com.example.harajtask.viewmodel.ViewModelActivity
import com.example.harajtask.viewmodel.ViewModelFactory
import kotlinx.coroutines.*
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var provider: ViewModelActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        recycler = findViewById(R.id.ad_item)

        provider = ViewModelProvider(
            this,
            ViewModelFactory(Repository(Api(this)))
        ).get(ViewModelActivity::class.java)
        provider.getData().observe(this, Observer<List<Data>> {
            adapter.addData(it)
        })

        // UI Setup
        adapter = DataAdapter(provider.getData().value ?: emptyList(), this)
        recycler.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
    }

    override fun onResume() {
        provider.fetchData()
        super.onResume()
    }
}