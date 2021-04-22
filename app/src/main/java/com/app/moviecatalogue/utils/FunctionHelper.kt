package com.app.moviecatalogue.utils

import android.annotation.SuppressLint
import android.content.Context
import com.app.moviecatalogue.R
import java.text.SimpleDateFormat
import java.util.*

fun String.toImageurl(): String = "https://image.tmdb.org/t/p/w500$this"

@SuppressLint("SimpleDateFormat")
fun String?.dateFormat(context: Context): String {
    return if (this == null || this.isEmpty()) {
        context.getString(R.string.no_data)
    } else {
        val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("en", "US"))
        val newDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale("en", "US"))
        val currentDate = currentDateFormat.parse(this)
        newDateFormat.format(currentDate!!)
    }
}

fun List<Int>.toStringList(): String {
    val sb = StringBuilder()
    for (i in this.indices) {
        if (i == 0) {
            sb.append(this[i].runtimeFormat())
        } else {
            sb.append(", ${i.runtimeFormat()}")
        }
    }
    return sb.toString()
}

fun Int.runtimeFormat(): String {
    return if (this > 60) {
        val hours = this / 60
        val minutes = this % 60
        "$hours hours $minutes minutes"
    } else {
        "$this minutes"
    }
}

