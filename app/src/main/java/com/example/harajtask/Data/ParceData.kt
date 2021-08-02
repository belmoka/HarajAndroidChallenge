package com.example.harajtask.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParceData(var title: String,
                var username: String,
                var thumbUrl: String,
                var commentCount: Int,
                var city: String,
                var date: Int,
                var body: String): Parcelable