package com.mailplug.exam.ui.daily.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mailplug.exam.utils.DateFormat
import com.mailplug.exam.utils.Keys
import java.util.*
import kotlin.collections.ArrayList


class CalendarListViewModel : ViewModel() {
    private var mCurrentTime: Long = 0
    var mTitle = MutableLiveData<String>()
    var mCalendarList: MutableLiveData<ArrayList<Any>> = MutableLiveData(ArrayList())

    fun setTitle(position: Int) {
        try {
            val item = mCalendarList.value!![position]
            if (item is Long) {
                setTitle(item)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setTitle(time: Long) {
        mCurrentTime = time
        mTitle.value = DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT)
    }

    fun initCalendarList(year: Int, month: Int) {
        val cal = GregorianCalendar()
        setCalendarList(cal, year, month)
    }

    private fun setCalendarList(cal: GregorianCalendar, year: Int, month: Int) {
        setTitle(cal.timeInMillis)
        val calendarList: ArrayList<Any> = ArrayList()
        try {
            val calendar = GregorianCalendar(year, month, 1, 0, 0, 0)
            calendarList.add(calendar.timeInMillis)
            //해당 월에 시작하는 요일 -1 을 하면 빈칸
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
            // 해당 월에 마지막 요일
            val max: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            for (j in 0 until dayOfWeek) {
                calendarList.add(Keys.EMPTY)
            }
            for (j in 1..max) {
                calendarList.add(
                    GregorianCalendar(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        j
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mCalendarList.value = calendarList
    }
}