package com.mailplug.exam.utils

import java.text.SimpleDateFormat
import java.util.*


object DateFormat {
    const val CALENDAR_HEADER_FORMAT = "yyyy년 MM월"
    const val DAY_FORMAT = "d"

    fun getDate(date: Long, pattern: String?): String {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
            val d = Date(date)
            formatter.format(d).toUpperCase(Locale.ROOT)
        } catch (e: Exception) {
            " "
        }
    }
}