package com.example.harajtask.Utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utility {
    fun progressCircle(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    fun setDate(date: Long): String? {
        val time = Date(date * 1000)
        val format: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mma")
        return format.format(time)
    }


}