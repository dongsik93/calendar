package com.mailplug.exam.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mailplug.exam.data.TSLiveData
import com.mailplug.exam.utils.DateFormat
import com.mailplug.exam.utils.Keys
import java.util.*
import kotlin.collections.ArrayList


class CalendarListViewModel : ViewModel() {
    private var mCurrentTime: Long = 0
    var mTitle = TSLiveData<String>()
    var mCalendarList: TSLiveData<ArrayList<Any>> = TSLiveData(ArrayList())
    var mCenterPosition = 0

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

    fun initCalendarList() {
        val cal = GregorianCalendar()
        setCalendarList(cal)
    }

    private fun setCalendarList(cal: GregorianCalendar) {
        setTitle(cal.timeInMillis)
        val calendarList: ArrayList<Any> = ArrayList()
        for (i in -300..299) {
            try {
                val calendar = GregorianCalendar(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + i,
                    1,
                    0,
                    0,
                    0
                )
                if (i == 0) {
                    mCenterPosition = calendarList.size
                }
                calendarList.add(calendar.timeInMillis)
                val dayOfWeek: Int =
                    calendar.get(Calendar.DAY_OF_WEEK) - 1 //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                val max: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) // 해당 월에 마지막 요일
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
        }
        mCalendarList.value = calendarList
    }
}