package com.example.harajtask.Api

import android.content.Context
import org.json.JSONArray
import java.io.BufferedReader

class Api(private var context: Context) {

    fun getData(): JSONArray {
        val file = context.assets.open("data.json")
        val buffer: String = file.bufferedReader().use(BufferedReader::readText).toString()
        println(buffer)
        val json = JSONArray(buffer)
        return json
    }


}