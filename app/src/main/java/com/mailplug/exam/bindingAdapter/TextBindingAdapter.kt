package com.mailplug.exam.bindingAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mailplug.exam.utils.DateFormat
import java.util.*


object TextBindingAdapter {
    fun setCalendarHeaderText(view: TextView, date: Long?) {
        try {
            date?.let {
                view.text = DateFormat.getDate(it, DateFormat.CALENDAR_HEADER_FORMAT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDayText(view: TextView, calendar: Calendar?) {
        try {
            calendar?.let {
                val gregorianCalendar = GregorianCalendar(
                    it.get(Calendar.YEAR),
                    it.get(Calendar.MONTH),
                    it.get(Calendar.DAY_OF_MONTH),
                    0,
                    0,
                    0
                )
                view.text = DateFormat.getDate(
                    gregorianCalendar.timeInMillis,
                    DateFormat.DAY_FORMAT
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}