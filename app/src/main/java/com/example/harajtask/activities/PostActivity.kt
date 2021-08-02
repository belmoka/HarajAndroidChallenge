package com.example.harajtask.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.harajtask.Data.ParceData
import com.example.harajtask.R
import com.example.harajtask.Utilities.Utility
import com.example.harajtask.viewmodel.PostModelFactory
import com.example.harajtask.viewmodel.PostViewModel

class PostActivity: AppCompatActivity() {

    private lateinit var postModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_details)
        val item = intent.getParcelableExtra<ParceData>("post")
        println(item!!.body)

        val title: TextView = findViewById(R.id.post_title)
        val body: TextView = findViewById(R.id.post_body)
        val username: TextView = findViewById(R.id.post_username)
        val city: TextView = findViewById(R.id.post_city)
        val date: TextView = findViewById(R.id.post_date)
        val contact: TextView = findViewById(R.id.phone_number)
        val postImage: ImageView = findViewById(R.id.post_image)
        val share: ImageView = findViewById(R.id.share_button)

        postModel = ViewModelProvider(this, PostModelFactory(item))
            .get(PostViewModel::class.java)
        postModel.getPost().observe(this, androidx.lifecycle.Observer {
            title.text = it.title
            Glide.with(this)
                .load(it.thumbUrl)
                .placeholder(Utility.progressCircle(this))
                .centerCrop()
                .into(postImage)
            body.text = it.body
            username.text = it.username
            city.text = it.city
            date.text = Utility.setDate(it.date.toLong())
        })

        contact.setOnClickListener(View.OnClickListener {
            val n: Int = R.string.phone
            print(n.toInt())
            call(this.resources.getString(R.string.phone))
        })
        share.setOnClickListener(View.OnClickListener {
            post()
        })
    }

    fun call(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone, null))
        startActivity(intent)
    }

    fun post() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Take a look at this famous item: https://haraj.com.sa/")
            type = "text/plain"
        }
        val chooser = Intent.createChooser(intent, "Share with your friend!")
        startActivity(chooser)
    }
}
