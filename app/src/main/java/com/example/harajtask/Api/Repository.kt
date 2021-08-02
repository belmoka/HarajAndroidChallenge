package com.example.harajtask.Api

import com.example.harajtask.Data.Data
import org.json.JSONArray

class Repository(private val api: Api) {

    fun setData(): ArrayList<Data> {
        val jsonArray: JSONArray = api.getData()
        val data = ArrayList<Data>()
        for (a in 0 until jsonArray.length()) {
            data.add(
                Data (
                    jsonArray.getJSONObject(a).getString("title"),
                    jsonArray.getJSONObject(a).getString("username"),
                    jsonArray.getJSONObject(a).getString("thumbURL"),
                    jsonArray.getJSONObject(a).getString("commentCount").toInt(),
                    jsonArray.getJSONObject(a).getString("city"),
                    jsonArray.getJSONObject(a).getString("date").toInt(),
                    jsonArray.getJSONObject(a).getString("body")
                )
            )
        }
        return data
    }
}